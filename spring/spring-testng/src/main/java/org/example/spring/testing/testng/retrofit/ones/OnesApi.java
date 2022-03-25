package org.example.spring.testing.testng.retrofit.ones;

import org.example.spring.testing.testng.ones.dto.IdProperty;
import org.example.spring.testing.testng.ones.dto.NextStage;
import org.example.spring.testing.testng.ones.dto.OnesPage;
import org.example.spring.testing.testng.ones.dto.SubType;
import org.example.spring.testing.testng.ones.dto.ViewScheme;
import org.example.spring.testing.testng.ones.dto.dsl.DSLRequest;
import org.example.spring.testing.testng.ones.enumerations.OnesType;
import org.example.spring.testing.testng.ones.enumerations.ViewType;
import org.example.spring.testing.testng.ones.response.OnesResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

/**
 * @author anyuan
 * @date 2022-03-15 20:59
 */
public interface OnesApi {

    /**
     * 查询视图
     *
     * @param projectId
     * @param viewType
     * @param subtypeId
     * @param transitionId
     * @return
     */
    @GET("/api/1.0/ones/viewScheme")
    Call<OnesResponse<ViewScheme>> viewScheme(@Query("projectId") Long projectId,
                                              @Query("viewType") ViewType viewType,
                                              @Query("subtypeId") Long subtypeId,
                                              @Query("transitionId") Long transitionId);

    /**
     * 查询子类型
     *
     * @param projectId
     * @param objectType
     * @param active
     * @return
     */
    @GET("/api/1.0/ones/listSubtype/{projectId}")
    Call<OnesResponse<OnesPage<SubType>>> listSubType(@Path("projectId") Long projectId,
                                                      @Query("objectType") OnesType objectType,
                                                      @Query("active") Boolean active);

    /**
     * 查询工作项详情
     *
     * @param issueId
     * @return
     */
    @GET("/api/1.0/ones/issue/{issueId}")
    Call<OnesResponse<Map<String, Object>>> getIssue(@Path("issueId") Long issueId);

    /**
     * 查询可流转状态
     *
     * @param issueType
     * @param issueId
     * @return
     */
    @GET("/api/1.0/ones/issue/nextStages")
    Call<OnesResponse<List<NextStage>>> nextStages(@Query("issueId") Long issueId,
                                                       @Query("issueType") OnesType issueType);

    /**
     * 创建工作项
     *
     * @param projectId
     * @param param
     * @return
     */
    @POST("/api/1.0/ones/projects/{projectId}/issue")
    Call<OnesResponse<Map<String, Object>>> createIssue(@Path("projectId") Long projectId,
                                                        @Body Map<String, Object> param);

    /**
     * 工作项状态流转
     *
     * @param issueId
     * @param transitionId
     * @param objectType
     * @param transitionObject
     * @return
     */
    @POST("/api/1.0/ones/issue/run/{issueId}/{transitionId}")
    Call<OnesResponse<Map<String, Object>>> transitIssue(@Path("issueId") Long issueId,
                                                         @Path("transitionId") Long transitionId,
                                                         @Query("issueType") OnesType objectType,
                                                         @Body Map<String, Object> transitionObject);

    /**
     * 删除工作项
     *
     * @param issueId
     * @return
     */
    @DELETE("/api/1.0/ones/issue/{issueId}")
    Call<OnesResponse<IdProperty>> deleteIssue(@Path("issueId") Long issueId);

    /**
     * 过滤器查询
     *
     * @param issueTypes
     * @param nameLike
     * @param username
     * @param orderField
     * @param orderKind
     * @param includeFatherChain
     * @param cn
     * @param sn
     * @param dslRequest
     * @return
     */
    @POST("/api/1.0/ones/issues/searchV2")
    Call<OnesResponse<OnesPage<Map<String, Object>>>> issueSearchV2(@Query("type") List<String> issueTypes,
                                                                    @Query("name") String nameLike,
                                                                    @Query("username") String username,
                                                                    @Query("orderField") String orderField,
                                                                    @Query("orderKind") String orderKind,
                                                                    @Query("includeFatherChain") Boolean includeFatherChain,
                                                                    @Query("cn") Integer cn,
                                                                    @Query("sn") Integer sn,
                                                                    @Body DSLRequest dslRequest);
}
