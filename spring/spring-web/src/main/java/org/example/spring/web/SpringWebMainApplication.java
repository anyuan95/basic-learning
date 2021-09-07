package org.example.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author anyuan
 * @since 2021-07-14 16:32
 */
@SpringBootApplication
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SpringWebMainApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SpringWebMainApplication.class, args);
        System.out.println(context.getBean("owner"));
    }
}
