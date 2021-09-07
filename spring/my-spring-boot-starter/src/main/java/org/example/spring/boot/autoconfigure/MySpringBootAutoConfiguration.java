package org.example.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anyuan
 * @since 2021-09-07 15:01
 */
@Configuration
@EnableConfigurationProperties(value = {MyStarterProperties.class})
public class MySpringBootAutoConfiguration {

    @Bean(name = "owner")
    public String owner() {
        return "eyal";
    }
}
