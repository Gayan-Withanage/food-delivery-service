package com.fooddelivery.auth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilterRegistration(ApiKeyFilter filter) {
        FilterRegistrationBean<ApiKeyFilter> reg = new FilterRegistrationBean<>(filter);
        reg.addUrlPatterns("/*");
        reg.setOrder(1); // run early, before Spring Security's own filter chain
        return reg;
    }
}
