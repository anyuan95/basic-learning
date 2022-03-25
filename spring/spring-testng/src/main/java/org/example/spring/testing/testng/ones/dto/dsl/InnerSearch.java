package org.example.spring.testing.testng.ones.dto.dsl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author anyuan
 * @date 2021-11-10 14:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class InnerSearch extends Query {
    public static final String RELATION_AND = "AND";
    public static final String RELATION_OR = "OR";
    /**
     * 多个条件
     */
    private List<BasicQuery> innerSearchCondition;
    /**
     * 条件之间的关系
     */
    private String innerSearchConditionRelation;
}
