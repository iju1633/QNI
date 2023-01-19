package com.pipecoding.ImJaeWook_QNI_Server.config;

import com.pipecoding.ImJaeWook_QNI_Server.util.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginCheckInterceptor()) // 인터셉터 등록
//                .order(1) // 낮을 수록 먼저 호출
//                .addPathPatterns("/answer/**", "/question/**", "/word-cloud/**", "/setting/**"); //인터셉터를 적용할 url 패턴
//    }
}