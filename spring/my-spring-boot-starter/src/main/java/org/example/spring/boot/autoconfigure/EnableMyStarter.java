package org.example.spring.boot.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author anyuan
 * @since 2021-09-07 15:13
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MySpringBootAutoConfiguration.class)
public @interface EnableMyStarter {
}
