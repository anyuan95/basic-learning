package org.example.basic.java.jdbc.annotations;

import java.lang.annotation.*;

/**
 * @author anyuan
 * @since 2021-07-21 13:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableField {

    String value() default "";

    boolean exist() default true;
}
