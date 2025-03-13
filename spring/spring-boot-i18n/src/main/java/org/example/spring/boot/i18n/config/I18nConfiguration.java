package org.example.spring.boot.i18n.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author anyuan
 * @date 2025-02-13 10:30
 */
@SpringBootConfiguration
public class I18nConfiguration {

    // 覆盖默认的AcceptHeaderLocaleResolver
//    @Bean
//    public LocaleResolver localeResolver() {
//        final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
////        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//        // 等同于 Locale.SIMPLIFIED_CHINESE
//        localeResolver.setDefaultLocale(Locale.CHINA);
//        localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC+8"));
//        return localeResolver;
//    }

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 指定国际化文件位置
        messageSource.setBasename("lang/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

//    @Bean
//    public ICUMessageSource messageSource() {
//        ICUReloadableResourceBundleMessageSource messageSource = new ICUReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:lang/messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
}
