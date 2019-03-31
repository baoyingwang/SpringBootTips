package org.baoyingwang.springboottips.sample.helloworldproj.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
public class ResponseLogging extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //https://stackoverflow.com/questions/39935190/contentcachingresponsewrapper-produces-empty-response

        //we cannot get POST content of the request, so this is only for Response Logging. The RequstLogging.java will resolve that.
        //Caching is required because the data can be read just once. The caching makes use read the data without breaking that.
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);
        String responseContent = new String(responseWrapper.getContentAsByteArray(), UTF_8);
        log.info("track response - req uri:{}, status:{}, content type:{}, headers:{}, content size:{}, content:{}", request.getRequestURI(), responseWrapper.getStatus(), responseWrapper.getContentType(),
                new ServletServerHttpResponse(responseWrapper).getHeaders(), responseWrapper.getContentSize(), responseContent);

        responseWrapper.copyBodyToResponse();

    }


}
