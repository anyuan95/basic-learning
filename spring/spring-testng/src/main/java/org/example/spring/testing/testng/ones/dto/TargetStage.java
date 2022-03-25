package org.example.spring.testing.testng.ones.dto;

import lombok.Data;

/**
 * ones当前状态和下个可以流转的状态
 *
 * @author liyaozong02
 */
@Data
public class TargetStage {
    /**
     * 当前状态
     */
    private String currentStateName;

    /**
     * 下个可以流转的状态id
     */
    private Long id;

    /**
     * ones子类型id
     */
    private Long issueSubtypeId;

    /**
     * 下一个可以流转的状态id
     */
    private String nextStateName;
}