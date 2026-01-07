package org.example.spring.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author anyuan
 * @since 2021-07-14 16:32
 */
@Slf4j
@SpringBootApplication
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SpringWebMainApplication implements CommandLineRunner {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SpringWebMainApplication.class, args);
//        System.out.println(context.getBean("owner"));
    }

    @Value("${myName}")
    private String myName;

    @Override
    public void run(String... args) throws Exception {
        log.info("=====, {}", myName);
    }
}
