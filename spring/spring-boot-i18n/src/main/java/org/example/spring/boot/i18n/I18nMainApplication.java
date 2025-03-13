package org.example.spring.boot.i18n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;

/**
 * @author anyuan
 * @date 2025-02-13 10:29
 */
@SpringBootApplication
public class I18nMainApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(I18nMainApplication.class, args);
    }

}
