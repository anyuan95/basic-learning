package org.example.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author anyuan
 * @since 2021-07-14 16:32
 */
@SpringBootApplication
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SpringWebMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebMainApplication.class, args);
    }
}
