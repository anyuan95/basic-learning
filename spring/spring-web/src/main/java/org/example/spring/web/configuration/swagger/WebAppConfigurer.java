package org.example.spring.web.configuration.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * https://blog.csdn.net/weixin_43860532/article/details/111228599
 * https://www.jianshu.com/p/0f16c9f1760e
 *
 * @author anyuan
 * @date 2022-04-06 14:43
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 第一种方式是将 json 处理的转换器放到第一位并且是继承GenericHttpMessageConverter，这样json转换器就会进行处理 String转换器就处理不了了。
        converters.add(0, new MappingJackson2HttpMessageConverter());
        // 第二种就是把String类型的转换器去掉，会找到后面的转换器进行转换
        // converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass() == StringHttpMessageConverter.class);
    }
}
