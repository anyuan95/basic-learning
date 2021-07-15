package org.example.spring.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author anyuan
 * @since 2021-07-15 10:20
 */
@Configuration
@MapperScan(basePackages = {"org.example.spring.mybatis.dao.mapper"})
public class MybatisConfiguration {

}
