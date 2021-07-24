package org.example.spring.web.exception;

import org.example.spring.web.enums.ExceptionMessageEnum;

/**
 * @author anyuan
 * @since 2021-07-22 15:15
 */
public class BizException extends RuntimeException {

    private Integer code;
    private Object data;

    public BizException(String message) {
        super(message);
    }

    public BizException(ExceptionMessageEnum exceptionMessageEnum) {
        this(exceptionMessageEnum.message);
        this.code = exceptionMessageEnum.code;
    }

    public <T> BizException(ExceptionMessageEnum exceptionMessageEnum, T data) {
        this(exceptionMessageEnum);
        this.data = data;
    }

}
