package org.example.spring.web.enums;


/**
 * @author anyuan
 * @since 2021-03-02 10:10
 */
public enum ExceptionMessageEnum {

    ERROR(16000, "系统异常"),

    ;

    public Integer code;

    public String message;

    ExceptionMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
