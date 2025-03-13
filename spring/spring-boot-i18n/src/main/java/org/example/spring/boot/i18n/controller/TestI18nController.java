package org.example.spring.boot.i18n.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * @author anyuan
 * @date 2025-02-13 10:37
 */
@RestController
@RequestMapping("/i18n")
public class TestI18nController {

    @Resource
    private MessageSource messageSource;

    @GetMapping("/translate")
    public String translate(@RequestParam String key, @RequestParam List<String> params) {
        final Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null != params ? params.toArray(new String[0]) : null, locale);
    }

}
