package com.ex.user.config;

import com.ex.user.filter.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMVCConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .excludePathPatterns("/user/api/*/*/login")
                .excludePathPatterns("/user/api/*/*/login/code")
                .excludePathPatterns("/user/api/*/*/send/message")
                .excludePathPatterns("/user/api/*/*/register")
                .excludePathPatterns("/user/api/*/*/find/password")
                .excludePathPatterns("/user/api/*/*/check/*")
                .excludePathPatterns("/user/api/*/*/level/config")
                .excludePathPatterns("/user/api/*/*/member/config")
                .excludePathPatterns("/user/api/*/*/member/privilege")
                .excludePathPatterns("/user/api/*/*/task/config")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }


}