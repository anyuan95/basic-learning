package org.example.spring.web.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author anyuan
 * @since 2021-07-22 14:42
 */
@Data
@Builder
public class Response<T> implements Serializable {

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = 500;

    private Integer code;
    private boolean success;
    private String message;
    private T data;

    private String requestId;

    public static <T> Response<T> ofSuccess(T t) {
        return Response.<T>builder()
                .code(SUCCESS_CODE)
                .success(true)
                .data(t)
                .build();
    }

    public static <T> Response<T> ofFailure(String message) {
        return ofFailure(null, message);
    }

    public static <T> Response<T> ofFailure(Integer code, String message) {
        return Response.<T>builder()
                .code(Optional.ofNullable(code).orElse(ERROR_CODE))
                .success(false)
                .build();
    }

}
