package org.example.spring.testing.testng.ones.dto.dsl;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * DSL 查询条件部分
 * see https://km.sankuai.com/page/791591504#id-1.1%E9%80%9A%E7%94%A8%E7%9A%84%E8%BF%87%E6%BB%A4%E5%99%A8%E6%9F%A5%E8%AF%A2dsl
 *
 * @author anyuan
 * @date 2021-11-09 20:32
 */
@Data
@Builder
public class BasicQuery<T> extends Query {

    /**
     * 字段名
     */
    private String field;
    /**
     * 匹配方式
     *
     * TERM:单个字符串的精确匹配
     * TERMS:多个字符串的精确匹配
     * RANGE:范围查询，如id, time
     * MATCH:模糊查询
     */
    private String type;
    /**
     * 小于等于
     * type = RANGE 时使用
     */
    private Long lte;
    /**
     * 大于等于
     * type = RANGE 时使用
     */
    private Long gte;
    /**
     * 小于
     * type = RANGE 时使用
     */
    private Long lt;
    /**
     * 大于
     * type = RANGE 时使用
     */
    private Long gt;
    /**
     * 匹配值
     * type = TERM 或 MATCH 时使用
     */
    private T value;
    /**
     * 匹配值列表
     * type = TERMS 时使用
     */
    private List<T> valueList;
    /**
     * 且运算或者或运算，true的时候为且运算；false的时候为或运算
     * 默认值为true
     */
    private Boolean must = true;

}
