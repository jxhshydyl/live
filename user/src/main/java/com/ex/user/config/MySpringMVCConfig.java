package com.ex.user.config;

import com.ex.user.filter.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMVCConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .excludePathPatterns("/user/api/*/*/login")
                .excludePathPatterns("/user/api/*/*/login/code")
                .excludePathPatterns("/user/api/*/*/send/message")
                .excludePathPatterns("/user/api/*/*/register")
                .addPathPatterns("/**");
    }


}