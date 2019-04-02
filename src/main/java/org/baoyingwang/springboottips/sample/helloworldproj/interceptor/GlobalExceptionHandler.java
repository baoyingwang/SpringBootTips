package org.baoyingwang.springboottips.sample.helloworldproj.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.baoyingwang.springboottips.sample.helloworldproj.enums.RestBusinessErrorCode;
import org.baoyingwang.springboottips.sample.helloworldproj.json.SimpleResultResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public SimpleResultResponse handle(HttpServletRequest request, Throwable e) {
        log.error("Error occurs during :{} ,error stack", request.getRequestURI(), e);
        return SimpleResultResponse.newError(RestBusinessErrorCode.INTERNAL_ERROR.getCode(), e.getMessage());
    }
}
