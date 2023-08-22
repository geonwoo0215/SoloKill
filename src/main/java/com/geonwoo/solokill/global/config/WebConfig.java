package com.geonwoo.solokill.global.config;

import com.geonwoo.solokill.global.auth.AuthenticationInterceptor;
import com.geonwoo.solokill.global.auth.LoginInterceptor;
import com.geonwoo.solokill.global.auth.LoginMemberArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginMemberArgumentResolver loginMemberArgumentResolver;
    private final LoginInterceptor loginInterceptor;
    private final AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/post")
                .order(1);
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/post")
                .order(2);
    }


}
