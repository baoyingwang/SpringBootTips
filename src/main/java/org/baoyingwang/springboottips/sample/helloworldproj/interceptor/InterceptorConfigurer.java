package org.baoyingwang.springboottips.sample.helloworldproj.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//https://stackoverflow.com/questions/31082981/spring-boot-adding-http-request-interceptors

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Autowired
    private SignatureValidationInterceptor signatureHandler;

    @Autowired
    private RequestTraceIDInterceptor requestTraceIDInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //TODO - if addPathPatterns("/**") and the url include a wrong Params(e.g. missing a key param - chain), the interceptor will be triggered twice. Why?
        registry.addInterceptor(requestTraceIDInterceptor).addPathPatterns("/**");
        registry.addInterceptor(signatureHandler).addPathPatterns("/**");
    }
}



