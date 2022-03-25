package org.example.spring.testing.testng.ones.dto;

import lombok.Data;

import java.util.List;

/**
 * ones返回分页对象
 *
 * @author liyaozong02
 */
@Data
public class OnesPage<T> {

    /**
     * 当前页
     */
    private Integer cn;

    /**
     * 总页数
     */
    private Integer pn;

    /**
     * 页大小
     */
    private Integer sn;

    /**
     * 总条数
     */
    private Integer tn;

    /**
     * 明细信息
     */
    private List<T> items;
}
