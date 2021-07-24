package org.example.basic.java.jdbc.annotations;

import java.lang.annotation.*;

/**
 * @author anyuan
 * @since 2021-07-21 13:47
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableName {

    String value() default "";
}
