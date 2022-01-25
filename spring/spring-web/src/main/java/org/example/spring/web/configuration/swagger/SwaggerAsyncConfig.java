//package org.example.spring.web.configuration.swagger;
//
//import com.fasterxml.classmate.TypeResolver;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.context.annotation.Import;
//import org.springframework.plugin.core.config.EnablePluginRegistries;
//import springfox.documentation.schema.configuration.ModelsConfiguration;
//import springfox.documentation.service.PathDecorator;
//import springfox.documentation.spi.service.ApiListingBuilderPlugin;
//import springfox.documentation.spi.service.DefaultsProviderPlugin;
//import springfox.documentation.spi.service.DocumentationPlugin;
//import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
//import springfox.documentation.spi.service.OperationBuilderPlugin;
//import springfox.documentation.spi.service.OperationModelsProviderPlugin;
//import springfox.documentation.spi.service.ParameterBuilderPlugin;
//import springfox.documentation.spi.service.RequestHandlerProvider;
//import springfox.documentation.spi.service.ResourceGroupingStrategy;
//import springfox.documentation.spi.service.contexts.Defaults;
//import springfox.documentation.spring.web.DocumentationCache;
//import springfox.documentation.spring.web.ObjectMapperConfigurer;
//import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
//import springfox.documentation.spring.web.json.JsonSerializer;
//import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
//import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
//import springfox.documentation.spring.web.scanners.ApiDocumentationScanner;
//import springfox.documentation.swagger.configuration.SwaggerCommonConfiguration;
//
//import javax.servlet.ServletContext;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author anyuan
// * @date 2021-11-24 16:38
// */
//@Slf4j
//@Configuration
//@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "true", matchIfMissing = false)
//@Import({SwaggerCommonConfiguration.class, ModelsConfiguration.class})
//
//@ComponentScan(basePackages = {
//        "springfox.documentation.swagger2.web",
//        "springfox.documentation.swagger2.mappers",
//        "springfox.documentation.spring.web.scanners",
//        "springfox.documentation.spring.web.readers.operation",
//        "springfox.documentation.spring.web.readers.parameter",
//        "springfox.documentation.spring.web.plugins",
//        "springfox.documentation.spring.web.paths"
//}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
//        classes = {DocumentationPluginsBootstrapper.class})})
////禁用掉DocumentationPluginsBootstrapper，这样就不会自动扫描，卡主线程
//@EnablePluginRegistries({DocumentationPlugin.class,
//        ApiListingBuilderPlugin.class,
//        OperationBuilderPlugin.class,
//        ParameterBuilderPlugin.class,
//        ExpandedParameterBuilderPlugin.class,
//        ResourceGroupingStrategy.class,
//        OperationModelsProviderPlugin.class,
//        DefaultsProviderPlugin.class,
//        PathDecorator.class})
//public class SwaggerAsyncConfig implements ApplicationListener<ApplicationReadyEvent> {
//
//    @Bean
//    public Defaults defaults() {
//        return new Defaults();
//    }
//
//    @Bean
//    public DocumentationCache resourceGroupCache() {
//        return new DocumentationCache();
//    }
//
//    @Bean
//    public static ObjectMapperConfigurer objectMapperConfigurer() {
//        return new ObjectMapperConfigurer();
//    }
//
//    /**
//     * 自定义json序列化，解决value是null的时候依旧序列化问题
//     *
//     * @param moduleRegistrars
//     * @return
//     */
//    @Bean
//    public JsonSerializer jsonSerializer(List<JacksonModuleRegistrar> moduleRegistrars) {
//        return new FhsJsonSerializer(moduleRegistrars);
//    }
//
//    @Bean
//    public static PropertyPlaceholderConfigurer swaggerProperties() {
//        PropertyPlaceholderConfigurer properties = new PropertyPlaceholderConfigurer();
//        properties.setIgnoreUnresolvablePlaceholders(true);
//        return properties;
//    }
//
//    @Autowired
//    private DocumentationPluginsManager documentationPluginsManager;
//    @Autowired
//    private RequestHandlerProvider provider;
//    @Autowired
//    private DocumentationCache scanned;
//    @Autowired
//    private ApiDocumentationScanner resourceListing;
//    @Autowired
//    private TypeResolver typeResolver;
//    @Autowired
//    private ServletContext servletContext;
//
//    /**
//     * 当springboot 项目启动完成后，新启动一个线程去扫描swagger相关注解，生成配置对象
//     *
//     * @param event
//     */
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        try {
//            List<RequestHandlerProvider> handlerProviders = new ArrayList<>();
//            handlerProviders.add(provider);
//            new Thread(() -> {
//                // 手动去扫描
//                new DocumentationPluginsBootstrapper(documentationPluginsManager,
//                        handlerProviders, scanned, resourceListing, typeResolver, defaults(), servletContext).start();
//                log.info("-------------swagger 异步扫描完成-------------");
//            }).start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
