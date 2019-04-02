package org.baoyingwang.springboottips.sample.helloworldproj.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
class SignatureValidationInterceptor extends HandlerInterceptorAdapter {


    @Value("${sig.check.sigValidDuration:300}")
    private int sigValidDuration;

    @Value("${sig.check.includePort:true}")
    private boolean sigIncludePort;


    //for easy during test
    @Value("${interceptor.skip.sig.check:false}")
    private boolean skipSigCheck;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        if (skipSigCheck) {
            return true;
        }

        //DO something here

        return true;
    }


    //after the handler is executed
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {
    }

}