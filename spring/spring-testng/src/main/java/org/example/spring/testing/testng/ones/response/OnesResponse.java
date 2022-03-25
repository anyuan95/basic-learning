package org.example.spring.testing.testng.ones.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author anyuan
 * @date 2022-03-15 21:04
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OnesResponse<T> {

    private Integer code;

    private String message;

    private T data;

}
