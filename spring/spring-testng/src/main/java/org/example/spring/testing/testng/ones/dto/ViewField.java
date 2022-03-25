package org.example.spring.testing.testng.ones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author anyuan
 * @date 2022-01-05 16:15
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewField<T> {
    private Long id;
    private String name;
    private Long viewGroupId;
    private Integer orderInViewGroup;
    private String typeName;

    /**
     * 底框提示
     */
    private String placeholder;
    /**
     * 小圆点提示
     */
    private String popoverHelper;
    /**
     * 校验规则，正则
     */
    private String validateRule;
    /**
     * 校验失败提示
     */
    private String validatePrompt;
    /**
     * 取值规则
     */
    private String valueRule;
    /**
     * 可选值覆盖
     */
    private String possibleValues;
    /**
     * 默认值 可能是JSONObject，也可能是JSONArray
     */
    private T defaultValue;
    /**
     * 是否显示可删除按钮
     * 默认可以删除
     */
    private Boolean showDelete;
    /**
     * 默认值是否可编辑
     */
    private Boolean defaultValueCanEdit;
    private Boolean needTimeDynamicSelect;
    private Boolean fromSubtypeDefaultView;
    /**
     * 字段的默认值配置
     */
    private Map<String, List<Map<String, Object>>> defaultValueConfig;
    private Map<String, Integer> rawStringIntegerMap;
    private String idField;
    private String type;
    private String variable;
    private String realVariable;

    private Boolean canSearch;
    private Boolean canOrder;
    private Boolean canEdit;
    private Boolean remote;
    private Boolean useAllEmployee;
    private Long createdAt;
    private String createdBy;
    private Long updatedAt;
    private String updatedBy;
    private String scope;
    private String valueField;
    private Long length;
    private String unit;
    private String fieldResponseType;
    private Long edId;

    private Boolean active;
    private Boolean sortable;
    private Boolean editable;
    private Boolean required;
    private Boolean multiple;
}
