package org.example.spring.testing.testng.ones.dto.dsl;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ONES DSL 过滤器 查询模型
 *
 * @author anyuan
 * @date 2021-11-09 20:32
 */
@Data
public class DSLRequest {
    /**
     * 要返回的字段列表
     */
    private List<String> displayFieldList;
    /**
     * 查询条件
     */
    private List<Query> query;

    public DSLRequest() {
        this.displayFieldList = new ArrayList<>();
        this.query = new ArrayList<>();
    }

    public DSLRequest addQuery(Query query) {
        this.query.add(query);
        return this;
    }
}
