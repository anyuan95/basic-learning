package org.example.basic.java.jdbc.annotations;

import java.lang.annotation.*;

/**
 * @author anyuan
 * @since 2021-07-21 13:54
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableId {

    String value() default "";
}
