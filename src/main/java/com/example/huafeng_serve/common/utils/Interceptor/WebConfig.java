package com.example.huafeng_serve.common.utils.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
// 注册拦截器
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecretKeyInterceptor())
                .excludePathPatterns("/wallpaper/**")
                .addPathPatterns("/**"); // 拦截所有路径

    }
}
