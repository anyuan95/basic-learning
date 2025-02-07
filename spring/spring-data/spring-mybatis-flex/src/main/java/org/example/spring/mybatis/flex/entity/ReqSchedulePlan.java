package org.example.spring.mybatis.flex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author anyuan
 * @since 2023-07-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "req_schedule_plan")
public class ReqSchedulePlan implements Serializable {

    /**
     * 主键id，自增，唯一
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 排期计划名称
     */
    private String name;

    /**
     * 排期计划描述
     */
    private String description;

    /**
     * 项目类型id
     */
    private Integer projectType;

    /**
     * 项目等级
     */
    private String projectLevel;

    /**
     * pmis项目id
     */
    private Long pmisProjectId;

    /**
     * 关联pmis项目等级
     */
    private String pmisProjectLevel;

    /**
     * 是否关联TG
     */
    private Boolean isRelatedToTg;

    /**
     * 项目主R
     */
    private String mainR;

    /**
     * 项目主R所属部门id
     */
    private Long mainROrgId;

    /**
     * 主R
     */
    private String mainResponsible;

    /**
     * 项目主R所属部门id
     */
    private Long mainResponsibleOrgId;

    /**
     * 技术主Rs
     */
    private String techMainRs;

    /**
     * 技术主R所属部门ids（特殊处理）
     */
    private String techMainROrgIdsWithSign;

    /**
     * 测试主Rs
     */
    private String qaMainRs;

    /**
     * 测试主R所属部门ids（特殊处理）
     */
    private String qaMainROrgIdsWithSign;

    /**
     * 业务主Rs
     */
    private String bizMainRs;

    /**
     * 业务主R所属部门ids（特殊处理）
     */
    private String bizMainROrgIdsWithSign;

    /**
     * 产品主Rs
     */
    private String prodMainRs;

    /**
     * 产品主R所属部门ids（特殊处理）
     */
    private String prodMainROrgIdsWithSign;

    /**
     * 技术主R
     */
    private String techMainResponsible;

    /**
     * 技术主R所属部门id
     */
    private Long techMainResponsibleOrgId;

    /**
     * qa主R
     */
    private String qaMainResponsible;

    /**
     * 测试主R所属部门id
     */
    private Long qaMainResponsibleOrgId;

    /**
     * 归属组织id
     */
    private Long belongOrgId;

    /**
     * 所属bg部门id
     */
    private Long bgOrgId;

    /**
     * 抄送人列表，允许0-n个，json格式
     */
    private String cc;

    /**
     * 抄送人，逗号分割
     */
    private String ccWithComma;

    /**
     * 整体开始时间
     */
    private LocalDateTime expectStart;

    /**
     * 整体结束时间
     */
    private LocalDateTime expectEnd;

    /**
     * 是否已关闭
     */
    private Boolean isClosed;

    /**
     * 是否已归档
     */
    private Boolean isArchived;

    /**
     * 是否已被删除
     */
    private Boolean deleteFlag;

    /**
     * 其他属性
     */
    private String additionalProperties;

    /**
     * 所有参与人
     */
    private String allParticipants;

    /**
     * 参与人对应组织列表
     */
    private String allParticipantOrgIds;

    /**
     * 参与人对应组织列表（特殊处理）
     */
    private String allParticipantOrgIdsWithSign;

    /**
     * 创建来源
     */
    private String createSource;

    /**
     * 创建人mis
     */
    private String createUser;

    /**
     * 创建人所属部门id
     */
    private Long createUserOrgId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最新修改人mis
     */
    private String updateUser;

    /**
     * 最新修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 大象群id
     */
    private Long dxGroupId;

    /**
     * 排期计划描述
     */
    private String remark;

    /**
     * tgId
     */
    private Long tgId;

}
