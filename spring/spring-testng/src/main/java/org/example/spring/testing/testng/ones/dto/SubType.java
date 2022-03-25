package org.example.spring.testing.testng.ones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring.testing.testng.ones.enumerations.OnesType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubType {

    /**
     * ones号
     */
    private Long id;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述信息
     */
    private String desc;
    /**
     * 类型
     */
    private OnesType objectType;
    /**
     * 创建时间
     */
    private Long createdAt;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 修改时间
     */
    private Long updatedAt;
    /**
     * 最近修改人
     */
    private String updatedBy;
    /**
     * 是否是默认模板
     */
    private Boolean isDefault;
    /**
     * 是否是激活状态
     */
    private Boolean active;
    /**
     * 模板（内容）
     */
    private String template;

//    private Long workflowDescId;
//    private String category;
//    private Integer fieldGroupId;
//    private Boolean subtypeDetailCustomizable;
//    private Object projectTemplate;

}