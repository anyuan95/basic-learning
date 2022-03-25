package org.example.spring.testing.testng;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.testing.testng.ones.dto.IdProperty;
import org.example.spring.testing.testng.ones.dto.NextStage;
import org.example.spring.testing.testng.ones.dto.OnesPage;
import org.example.spring.testing.testng.ones.dto.SubType;
import org.example.spring.testing.testng.ones.dto.TargetStage;
import org.example.spring.testing.testng.ones.dto.ViewField;
import org.example.spring.testing.testng.ones.dto.ViewScheme;
import org.example.spring.testing.testng.ones.dto.dsl.BasicQuery;
import org.example.spring.testing.testng.ones.dto.dsl.DSLRequest;
import org.example.spring.testing.testng.ones.enumerations.OnesConstant;
import org.example.spring.testing.testng.ones.enumerations.OnesType;
import org.example.spring.testing.testng.ones.enumerations.ViewType;
import org.example.spring.testing.testng.ones.response.OnesResponse;
import org.example.spring.testing.testng.retrofit.ones.OnesApi;
import org.example.spring.testing.testng.utils.JsonUtils;
import org.example.spring.testing.testng.utils.RetrofitUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.spring.testing.testng.ones.enumerations.ViewType.CREATE_VIEW;
import static org.example.spring.testing.testng.ones.enumerations.ViewType.TRANSITION_VIEW;

/**
 * @author anyuan
 * @date 2022-03-15 19:53
 */
@SpringBootTest(classes = TestngMainApplication.class)
@WebAppConfiguration
@Slf4j
@Test(groups = "ones-test")
public class OnesTest extends AbstractTestNGSpringContextTests {

    /**
     * ones工作项内部字段列表
     */
    private static final List<String> IGNORE_FIELD_VARIABLES = Lists.newArrayList("projectId", "subtypeId");
    // 归属项目空间
    private static final Long BELONG_PROJECT_ID = 31858L;
    // 需求分类
    private static final Long TEST_SET_ID = 278569L;
    private static final List<Long> ITERATION_ID_LIST = Arrays.asList(null, 30246L, 30247L);

    @Resource
    private OnesApi onesApi;

    /**
     * 拿到指定类型工作项的所有启用了的子类型(id)
     */
    private List<SubType> fetchSubTypes(Long projectId, OnesType onesType) {
        final OnesResponse<OnesPage<SubType>> subTypeResponse = RetrofitUtils.syncExecuteWithAssertion(
                onesApi.listSubType(projectId, onesType, true));
        Assert.assertNotNull(subTypeResponse, "查询子类型响应结果为空");
        Assert.assertTrue(subTypeResponse.getCode() >= 200 && subTypeResponse.getCode() < 300, "查询子类型返回码不是2xx");
        Assert.assertNotNull(subTypeResponse.getData(), "查询子类型响应结果data为空");
        final List<SubType> subTypes = subTypeResponse.getData().getItems();
        Assert.assertTrue(CollUtil.isNotEmpty(subTypes), "查询子类型响应结果items为空");
        return subTypes.stream()
                .filter(SubType::getActive)
                .filter(subType -> null != subType.getId())
                .collect(Collectors.toList());
    }

    /**
     * 查询指定子类型的创建视图详情
     *
     * @return
     */
    @Deprecated
    private ViewScheme getCreateViewScheme(Long projectId, Long subtypeId) {
        return getViewScheme(projectId, CREATE_VIEW, subtypeId, null);
    }

    @DataProvider(name = "allIssueTypeAllSubTypeCreateViewSchemes")
    private Object[][] getAllCreateViewSchemes() {
        final Long projectId = 29616L;
        final List<OnesType> onesTypes = Arrays.asList(OnesType.REQUIREMENT, OnesType.DEVTASK);
        final List<Object[]> answer = new ArrayList<>();
        for (OnesType onesType : onesTypes) {
            final List<SubType> subTypes = fetchSubTypes(projectId, onesType);
            for (SubType subType : subTypes) {
                final ViewScheme viewScheme = getViewScheme(projectId, CREATE_VIEW, subType.getId(), null);
                answer.add(new Object[]{projectId, subType.getId(), onesType, viewScheme});
            }
        }
        return answer.toArray(new Object[0][0]);
    }

    @Test(testName = "createNewIssue", dataProvider = "allIssueTypeAllSubTypeCreateViewSchemes",
            threadPoolSize = 1, invocationCount = 1)
    public void testCreateNewIssue(Long projectId, Long subtypeId, OnesType onesType, ViewScheme viewScheme) {
        final Long issueId = createNewIssue(projectId, subtypeId, viewScheme, onesType, false);
        if (null != issueId) {
            deleteIssue(issueId);
        }
    }

    @DataProvider(name = "prepareDataForIssueTransition")
    private Object[][] prepareDataForIssueTransition() {
        final Long projectId = 29616L;
        final List<OnesType> onesTypes = Arrays.asList(OnesType.REQUIREMENT, OnesType.DEVTASK);
        final List<Object[]> answer = new ArrayList<>();
        for (OnesType onesType : onesTypes) {
            final List<SubType> subTypes = fetchSubTypes(projectId, onesType);
            for (SubType subType : subTypes) {
                final ViewScheme viewScheme = getViewScheme(projectId, CREATE_VIEW, subType.getId(), null);
                final Long issueId = createNewIssue(projectId, subType.getId(), viewScheme, onesType, true);
                answer.add(new Object[]{projectId, issueId, subType.getId()});
            }
        }
        return answer.toArray(new Object[0][0]);
    }

    @Test(testName = "transitIssue", dataProvider = "prepareDataForIssueTransition",
            threadPoolSize = 5, invocationCount = 1)
    public void testTransitIssue(Long projectId, Long issueId, Long subtypeId) {
        issueTransition(projectId, issueId, subtypeId);
        deleteIssue(issueId);
    }

    /**
     * 创建工作项
     *
     * @param projectId
     * @param subtypeId
     * @param viewScheme
     */
    public Long createNewIssue(Long projectId, Long subtypeId, ViewScheme viewScheme, OnesType onesType, boolean fillInAllRequiredFields) {
        final List<ViewField> allFields = getAllChangeableFields(viewScheme);
        // 去掉子类型和所属空间，创建时这两个字段特殊处理
        allFields.removeIf(field -> IGNORE_FIELD_VARIABLES.contains(field.getVariable()));
        final List<ViewField> requiredFields = allFields
                .stream()
                .filter(viewField -> Boolean.TRUE.equals(viewField.getRequired()))
                .collect(Collectors.toList());

        final Map<String, Object> issue = Maps.newHashMap();
        issue.put("type", onesType.name());
        issue.put("projectId", projectId);
        issue.put("subtypeId", subtypeId);
        issue.put("name", "工作项必填字段校验功能验证-" + System.currentTimeMillis());
        issue.put("assigned", "anyuan02");

        // 填入所有必填字段
        // 或者 从全部字段中随机选择[必填字段数，总字段数)个进行填充
        final List<ViewField> filteredFields = fillInAllRequiredFields ?
                requiredFields : randomGetFields(allFields, RandomUtil.randomInt(requiredFields.size(), allFields.size()));
        filteredFields.forEach(field -> makeRandomFieldValue(field, issue));

        final OnesResponse<Map<String, Object>> creationResponse = createIssue(projectId, issue);
        checkIfResponseAsExpected(creationResponse, requiredFields.stream()
                .filter(field -> !filteredFields.contains(field))
                .collect(Collectors.toList()));
        return MapUtil.getLong(creationResponse.getData(), "id");
        // 创建工作项正常的场景
//        Assert.assertTrue(createResponse.getCode() >= 200 && createResponse.getCode() < 300,
//                "创建工作项时的返回码不为2xx，响应信息: [" + createResponse.getMessage() + "]");
//        final Map<String, Object> data = createResponse.getData();
//        final Long issueId = MapUtil.getLong(data, "id");
//        Assert.assertNotNull(issueId, "创建工作项的返回值中没有id字段(issueId)");
    }

    /**
     * 需求状态流转
     */
    public void issueTransition(Long projectId, Long issueId, Long subtypeId) {
        // step1.随机选择要流转的目标状态
        final OnesResponse<List<NextStage>> nextStagesResponse = RetrofitUtils.syncExecuteWithAssertion(
                onesApi.nextStages(issueId, OnesType.REQUIREMENT));
        Assert.assertNotNull(nextStagesResponse, "查询可流转状态响应为空");
        Assert.assertNotNull(nextStagesResponse.getData(), "查询可流转状态内容为空");
        final List<NextStage> nextStages = nextStagesResponse.getData();
        if (CollUtil.isEmpty(nextStages)) {
            final Map<String, Object> issueInfoMap = getIssue(issueId);
            // 当前状态的名称
            final String stateName = JsonPath.read(issueInfoMap, "$.state.name");
            log.warn("工作项[{}]当前状态为[{}]，找不到可流转的目标状态，跳过", issueId, stateName);
            return;
        }

        final NextStage randomNextStage = nextStages.get(RandomUtil.randomInt(0, nextStages.size()));
        final TargetStage targetState = randomNextStage.getTargetState();
        final Long transitionId = targetState.getId();
        // step2.随机填写字段（或必填字段），进行流转
        doAndCheckIssueTransition(projectId, issueId, subtypeId, transitionId);
    }

    /**
     * 查询工作项
     *
     * @param issueId
     * @return
     */
    private Map<String, Object> getIssue(Long issueId) {
        final OnesResponse<Map<String, Object>> issueResponse =
                RetrofitUtils.syncExecuteWithAssertion(onesApi.getIssue(issueId));
        Assert.assertNotNull(issueResponse, "查询工作项详情响应为空");
        Assert.assertNotNull(issueResponse.getData(), "查询工作项详情响应为空");
        final Map<String, Object> issueInfoMap = issueResponse.getData();
        return issueInfoMap;
    }

    /**
     * 状态流转与必填字段检查
     * 返回流转是否成功
     *
     * @param projectId
     * @param issueId
     * @param subtypeId
     * @param transitionId
     */
    private boolean doAndCheckIssueTransition(Long projectId, Long issueId, Long subtypeId, Long transitionId) {
        final ViewScheme viewScheme = getViewScheme(projectId, TRANSITION_VIEW, subtypeId, transitionId);
        final List<ViewField> allFields = getAllChangeableFields(viewScheme);
        final List<ViewField> requiredFields = allFields
                .stream()
                .filter(viewField -> Boolean.TRUE.equals(viewField.getRequired()))
                .collect(Collectors.toList());

        final Map<String, Object> issue = Maps.newHashMap();
        // 从全部字段中随机选择[必填字段数，总字段数)个进行填充
        final List<ViewField> filteredFields = randomGetFields(allFields,
                requiredFields.size() == allFields.size() ? requiredFields.size() : RandomUtil.randomInt(requiredFields.size(), allFields.size()));
        filteredFields.forEach(field -> makeRandomFieldValue(field, issue));

        final Map<String, Object> transition = Maps.newHashMap();
        transition.put("issueId", issueId);
        transition.put("restIssue", issue);

        // 检查状态流转结果与预期是否一致
        final OnesResponse<Map<String, Object>> transitionResponse = transitIssue(issueId, transitionId, transition);

        checkIfTransitionResponseAsExpected(transitionResponse, issueId, requiredFields.stream()
                .filter(field -> !filteredFields.contains(field))
                .collect(Collectors.toList()));
        return transitionResponse.getCode() >= 200 && transitionResponse.getCode() < 300;
    }

    /**
     * 检查流转的响应结果是否与预期的一致
     * 流转时的错误信息需要根据工作项当前已经填写了的所有字段进行对比
     *
     * @param transitionResponse
     * @param requiredFields
     */
    private void checkIfTransitionResponseAsExpected(
            OnesResponse<Map<String, Object>> transitionResponse, Long issueId, List<ViewField> requiredFields) {
        final Map<String, Object> issue = getIssue(issueId);
        // 查到工作项当前参数内容，然后从必填字段中删掉所有已填写的非空的内容
        checkIfResponseAsExpected(transitionResponse, requiredFields.stream()
                .filter(field -> !issue.containsKey(field.getVariable())).collect(Collectors.toList()));
    }

    /**
     * 流转工作项
     *
     * @param issueId
     * @param transitionId
     * @param transition
     * @return
     */
    private OnesResponse<Map<String, Object>> transitIssue(Long issueId, Long transitionId, Map<String, Object> transition) {
        final OnesResponse<Map<String, Object>> transitionResponse = RetrofitUtils.syncExecuteWithAssertion(
                onesApi.transitIssue(issueId, transitionId, OnesType.REQUIREMENT, transition));
        log.info("流转工作项状态的请求信息：issueId=[{}]，transition=[{}]", issueId, JsonUtils.toJson(transition));
        log.info("流转工作项状态的响应信息: " + JsonUtils.toJson(transitionResponse));
        Assert.assertNotNull(transitionResponse, "流转工作项状态响应信息为空");
        return transitionResponse;
    }

    /**
     * 创建工作项
     *
     * @param projectId
     * @param issueMap
     * @return
     */
    private OnesResponse<Map<String, Object>> createIssue(Long projectId, Map<String, Object> issueMap) {
        final OnesResponse<Map<String, Object>> createResponse = RetrofitUtils.syncExecuteWithAssertion(onesApi.createIssue(projectId, issueMap));
        log.info("创建工作项的请求信息：projectId=[{}]，issue=[{}]", projectId, JsonUtils.toJson(issueMap));
        log.info("创建工作项的响应信息: " + JsonUtils.toJson(createResponse));
        Assert.assertNotNull(createResponse, "创建工作项响应信息为空");
        // 对返回内容的校验放在解析和检查的方法中
//        final Integer code = createResponse.getCode();
//        Assert.assertTrue(code >= 200 && code < 300, "创建工作项时的返回码不为2xx，响应信息：" + createResponse.getMessage());
//        Assert.assertNotNull(createResponse.getData(), "创建工作项响返回内容为空");
        return createResponse;
    }

    /**
     * 从视图中解析出所有可填写字段
     *
     * @param viewScheme
     * @return
     */
    private List<ViewField> getAllChangeableFields(ViewScheme viewScheme) {
        final List<ViewField> allFields = Optional.ofNullable(viewScheme.getViewGroupList()).orElse(Collections.emptyList())
                .stream()
                .flatMap(viewGroup -> viewGroup.getViewFieldList().stream())
                .collect(Collectors.toList());
        return allFields;
    }

    /**
     * 从视图中解析出所有必填字段
     *
     * @param viewScheme
     * @return
     */
    private List<ViewField> getAllRequiredFields(ViewScheme viewScheme) {
        return getAllChangeableFields(viewScheme)
                .stream()
                .filter(viewField -> Boolean.TRUE.equals(viewField.getRequired()))
                .collect(Collectors.toList());
    }

    /**
     * 查询视图
     *
     * @param projectId
     * @param viewType
     * @param subtypeId
     * @return
     */
    private ViewScheme getViewScheme(Long projectId, ViewType viewType, Long subtypeId, Long transitionId) {
        final OnesResponse<ViewScheme> viewSchemeResponse = RetrofitUtils.syncExecuteWithAssertion(
                onesApi.viewScheme(projectId, viewType, subtypeId, transitionId));
        Assert.assertNotNull(viewSchemeResponse, "查询视图详情响应结果为空");
        Assert.assertTrue(viewSchemeResponse.getCode() >= 200 && viewSchemeResponse.getCode() < 300, "查询视图详情返回码不是2xx");
        Assert.assertNotNull(viewSchemeResponse.getData(), "查询视图详情响应结果data为空");
        return viewSchemeResponse.getData();
    }

    /**
     * 随机取n个字段
     */
    private List<ViewField> randomGetFields(List<ViewField> fields, int n) {
        Assert.assertTrue(n <= fields.size(), "期望的必填字段数量超过了实际的必填字段数量");
        shuffle(fields);
        return fields.subList(0, n);
    }

    /**
     * 检查返回值是否与期望的一致
     * 样例：请填写{}
     */
    private void checkIfResponseAsExpected(OnesResponse<Map<String, Object>> response, List<ViewField> missingRequiredFields) {
        final Integer code = response.getCode();
        final String message = response.getMessage();

        if (CollUtil.isEmpty(missingRequiredFields)) {
            // 期望返回码是200或201
            Assert.assertTrue(code >= 200 && code < 300, "响应返回码不为2xx，响应信息：" + message);
        } else {
            final List<String> fieldMessages = StrUtil.split(StrUtil.subAfter(message, "请填写", false), '、')
                    .stream()
                    .map(slice -> slice.substring(StrUtil.lastIndexOfIgnoreCase(slice, "(") + 1,
                            StrUtil.lastIndexOfIgnoreCase(slice, ")")))
                    .collect(Collectors.toList());
            final List<String> expectedFieldVariables = missingRequiredFields.stream().map(field ->
                            Optional.ofNullable(field.getVariable())
                                    .filter(StrUtil::isNotBlank)
                                    .orElse(StrUtil.EMPTY))
                    .collect(Collectors.toList());
            Collections.sort(expectedFieldVariables);
            Collections.sort(fieldMessages);
            log.info("期望返回的参数错误信息：[{}]", expectedFieldVariables);
            log.info("实际返回的参数错误信息：[{}]", fieldMessages);
            expectedFieldVariables.forEach(fieldMessages::remove);
            // 期望返回的错误信息包括且只包括所有缺少的必填项
            Assert.assertTrue(fieldMessages.isEmpty(),
                    StrUtil.format("差异内容包括：期望返回的字段名[{}]，实际返回的字段变量名[{}]",
                            JsonUtils.toJson(expectedFieldVariables), JsonUtils.toJson(fieldMessages)));
        }
    }

    /**
     * 随机填合法值
     *
     * @param field
     * @param issue
     */
    private void makeRandomFieldValue(ViewField field, Map<String, Object> issue) {
        final String variable = field.getVariable();
        final Boolean multiple = field.getMultiple();
        final Object defaultValue = field.getDefaultValue();
        Object realValue;
        if (null != defaultValue) {
            // 有默认值用默认值
            if (defaultValue instanceof Map) {
                realValue = JsonPath.read(defaultValue, "$.value");
            } else {
                realValue = defaultValue;
            }
        } else {
            // 没有默认值就自己填
            final String type = field.getType();
            if ("component_time".equals(type)) {
                // 时间组件
                realValue = System.currentTimeMillis();
            } else if ("list".equals(type)) {
                // 枚举下拉框
                final Map<String, Integer> rawStringIntegerMap = Optional.ofNullable(field.getRawStringIntegerMap())
                        .filter(CollUtil::isNotEmpty)
                        .orElseThrow(() -> new RuntimeException("字段" + variable + "缺少可选值"));
                final ArrayList<Integer> availableValues = Lists.newArrayList(rawStringIntegerMap.values());
                realValue = availableValues.get(RandomUtil.randomInt(0, availableValues.size()));
            } else if ("int".equals(type)) {
                realValue = RandomUtil.randomInt(0, 100);
            } else if ("component_pmis_project".equals(type)) {
                // 归属pmis项目，都用非pmis
                realValue = -1;
            } else if ("component_user".equals(type)) {
                // 用户，可单可多，由multiple决定
                realValue = "anyuan02";
            } else if ("component_new_org".equals(type)) {
                // 选择部门，页面使用完整部门路径，传到后台就用orgId
                realValue = "155984";
            } else if ("component_version".equals(type)) {
                // 版本id，没有开放接口能查到，暂时用固定值
                realValue = 12937;
            } else if ("component_label".equals(type)) {
                // 标签id
                // 20844, 20845
                realValue = 20844;
            } else if ("component_classify".equals(type)) {
                // 需求分类id
                // 278569
                realValue = TEST_SET_ID;
            } else if ("component_belong".equals(type)) {
                // 关联空间id
                // 默认用当前空间
                realValue = BELONG_PROJECT_ID;
            } else if ("component_iteration".equals(type)) {
                // 关联迭代id
                // 30246, 30247
                realValue = ITERATION_ID_LIST.get(RandomUtil.randomInt(0, ITERATION_ID_LIST.size()));
            } else if ("component_parent".equals(type)) {
                // 父需求
                realValue = 65192691L;
            } else if ("float".equals(type)) {
                // 工时
                realValue = RandomUtil.randomDouble(1, 10);
            } else if ("text".equals(type)) {
                // 文本，排期结果
                realValue = "测试文本";
            } else {
                Assert.fail("无法解析的fieldType: [" + type + "]");
                return;
            }
        }
        putFieldIntoIssue(issue, variable, realValue, multiple);
    }

    private void putFieldIntoIssue(Map<String, Object> issue, String fieldName, Object oneValue, Boolean isMultiple) {
        if (Boolean.TRUE.equals(isMultiple)) {
            issue.put(fieldName, Collections.singletonList(oneValue));
        } else {
            issue.put(fieldName, oneValue);
        }
    }

    /**
     * 打乱list顺序
     *
     * @param list
     * @param <T>
     */
    private <T> void shuffle(List<T> list) {
        final int n = list.size();
        for (int i = n - 1; i >= 0; i--) {
            swap(list, i, RandomUtil.randomInt(0, i + 1));
        }
    }

    private <T> void swap(List<T> list, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * 删除工作项
     *
     * @param issueId
     */
    private void deleteIssue(Long issueId) {
        final OnesResponse<IdProperty> deletionResponse = RetrofitUtils.syncExecuteWithAssertion(onesApi.deleteIssue(issueId));
        Assert.assertNotNull(deletionResponse, "删除工作项响应信息为空");
        final Integer code = deletionResponse.getCode();
        Assert.assertTrue(code >= 200 && code < 300, "删除工作项时的返回码不为2xx，响应信息：" + deletionResponse.getMessage());
        Assert.assertNotNull(deletionResponse.getData(), "删除工作项返回内容为空");
    }

    @Test
    public void deleteOne() {
        final Long issueId = 65205322L;
        deleteIssue(issueId);
    }
    /**
     * DON'T use this method
     */
    @Test
    private void deleteOneByOne() {
        final Long projectId = 29616L;
        final String userName = "anyuan02";
        // DSL查询模型
        final DSLRequest dslRequest = new DSLRequest();
        dslRequest.setDisplayFieldList(ImmutableList.of(
                OnesConstant.OnesIssueDSLFieldConstant.ID,
                OnesConstant.OnesIssueDSLFieldConstant.NAME,
                OnesConstant.OnesIssueDSLFieldConstant.STATE
        ));
        dslRequest.addQuery(BasicQuery.builder()
                .field(OnesConstant.OnesIssueDSLFieldConstant.PROJECT_ID)
                .type(OnesConstant.OnesIssueDSLKeywordConstant.TERM)
                .value(projectId)
                .build());
        dslRequest.addQuery(BasicQuery.builder()
                .field(OnesConstant.OnesIssueDSLFieldConstant.ASSIGNED)
                .type(OnesConstant.OnesIssueDSLKeywordConstant.TERM)
                .value(userName)
                .build());
        dslRequest.addQuery(BasicQuery.builder()
                .field(OnesConstant.OnesIssueDSLFieldConstant.CREATED_BY)
                .type(OnesConstant.OnesIssueDSLKeywordConstant.TERM)
                .value("ep.waimai")
                .build());

        // 工作项必填字段校验功能验证 验证缺少字段
        final OnesResponse<OnesPage<Map<String, Object>>> queryResponse = RetrofitUtils.syncExecuteWithAssertion(
                onesApi.issueSearchV2(Arrays.asList(OnesType.REQUIREMENT.name(), OnesType.DEVTASK.name()), "必填字段验证",
                        userName, OnesConstant.OnesIssueDSLFieldConstant.CREATED_AT, "DESC", false,
                        1, 100, dslRequest));

        Assert.assertNotNull(queryResponse, "DSL查询响应信息为空");
        final Integer code = queryResponse.getCode();
        Assert.assertTrue(code >= 200 && code < 300, "DSL查询的返回码不为2xx，响应信息：" + queryResponse.getMessage());
        Assert.assertNotNull(queryResponse.getData(), "DSL查询返回内容为空");

        final List<String> excludeList = Arrays.asList();
        final List<Long> issueIdsToBeDeleted = queryResponse.getData().getItems().stream()
                .filter(item -> !excludeList.contains(MapUtil.getStr((Map<?, ?>) item.get("name"), "value")))
                .map(item -> MapUtil.getLong((Map<?, ?>) item.get("id"), "value")).collect(Collectors.toList());
        for (Long issueId : issueIdsToBeDeleted) {
            deleteIssue(issueId);
        }
    }


    public static void main(String[] args) {
        String slice = "技术主R(单选)";
        System.out.println(slice.substring(StrUtil.lastIndexOfIgnoreCase(slice, "(") + 1,
                StrUtil.lastIndexOfIgnoreCase(slice, ")")));
    }
}
