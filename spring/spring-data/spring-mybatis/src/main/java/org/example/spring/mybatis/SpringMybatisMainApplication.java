package org.example.spring.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author anyuan
 * @since 2021-07-15 11:09
 */
@SpringBootApplication
public class SpringMybatisMainApplication {

    public static void main(String[] args) {
        System.out.println("---------------------START----------------------");
        System.out.println(System.getProperty("java.class.path"));
        SpringApplication.run(SpringMybatisMainApplication.class, args);
    }
}
