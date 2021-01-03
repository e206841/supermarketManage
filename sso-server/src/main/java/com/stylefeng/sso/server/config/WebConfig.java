package com.stylefeng.sso.server.config;

import cn.stylefeng.guns.base.auth.properties.SsoProperties;
import cn.stylefeng.guns.sys.core.auth.cache.impl.DefaultSessionManager;
import cn.stylefeng.guns.sys.core.security.config.WebSecurityConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.stylefeng.sso.plugin.interceptor.SsoServerInterceptor;
//import com.stylefeng.sso.plugin.properties.SsoProperties;
import com.stylefeng.sso.server.api.SsoApi;
import com.stylefeng.sso.server.interceptor.SsoServerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * web配置
 *
 * @author fengshuonan
 * @Date 2018/8/29 下午3:32
 */
@ControllerAdvice
@Configuration
@Import({SsoProperties.class})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;


    @Autowired
    private SsoProperties ssoProperties;

    @Value("${cors.origins}")
    String origins;

    /**
     * 配置string解析器放在json解析器前边
     *
     * @author fengshuonan
     * @Date 2019/8/7 23:09
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        HttpMessageConverter<?> httpMessageConverter = converters.get(0);
        converters.remove(0);
        converters.add(2, httpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SsoServerInterceptor(ssoProperties))
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/ssoApi/authToken", "/logout", "/assets/**");
    }

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        //本应用
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");

        //流程设计器
        registry.addResourceHandler("/activiti-editor/**").addResourceLocations("classpath:/activiti-editor/");
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.messageConverters(fastJsonHttpMessageConverter);
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder().build();
    }


    /**
     * 跨域调用
     * @return
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList(origins.split(",")));
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

}
