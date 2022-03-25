package org.example.spring.testing.testng.ones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * ones下个可以流转的状态
 *
 * @author liyaozong02
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NextStage {
    /**
     * 当前状态
     */
    private String currentState;

    /**
     * 下一个可以流转的状态
     */
    private TargetStage targetState;

    /**
     * 不太确定这个字段的意思
     */
    private Boolean workTime;
}