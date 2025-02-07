package org.example.spring.mybatis.flex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author anyuan
 * @since 2021-07-15 11:09
 */
@SpringBootApplication
@MapperScan(basePackages = {"org.example.spring.mybatis.flex.mapper"})
public class SpringMybatisMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisMainApplication.class, args);
    }
}
