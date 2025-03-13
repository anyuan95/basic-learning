package org.example.spring.boot.i18n.resolver;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author anyuan
 * @date 2025-02-13 11:58
 */
public class PriorityLocaleResolver extends AbstractLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 优先从query中获取lang
        final String fromQuery = request.getParameter("lang");
        // 其次从header中取AcceptLanguage
        final String fromHeaderAcceptLanguage = request.getHeader("Accept-Language");
        // 最后从header中取lang
        final String fromHeaderLang = request.getHeader("lang");

        // 逐个进行解析，取第一个有效的使用。如果都拿不到则使用default
        // TODO 参考nestjs-i18n的实现，提供多种解析器，按照优先级取
        // https://nestjs-i18n.com/quick-start#add-resolvers

        return super.getDefaultLocale();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        LocaleContextHolder.setLocale(locale);
    }
}
