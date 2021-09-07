package org.example.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author anyuan
 * @since 2021-09-07 15:07
 */
@Data
@ConfigurationProperties(prefix = "eyal", ignoreUnknownFields = true)
public class MyStarterProperties {

    private RedisConfig redis;

    @Data
    public static class RedisConfig {
        private String username;
        private String password;
        private Integer database;
    }
}
