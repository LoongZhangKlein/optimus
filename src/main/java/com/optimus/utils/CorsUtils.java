package com.optimus.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域过滤器配置类   自动运行的
 */
@Configuration
public class CorsUtils {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 允许那些域访问
        corsConfiguration.addAllowedOrigin("*");
        //请求头
        corsConfiguration.addAllowedHeader("*");
        //请求头方式 GET POST 等类型
        corsConfiguration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        //映射路径
        source.registerCorsConfiguration("/**", corsConfiguration);
        //创建CorsFilter
        return new CorsFilter(source);

    }

}
