package org.baoyingwang.springboottips.sample.helloworldproj.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * to make  this work, you have to set
 * logging.level.org.baoyingwang.springboottips.sample.helloworldproj.interceptor.RequestLogging=DEBUG
 * and
 * set jvm option: -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager
 * note: this jvm option is to make sure the internal spring logging will be replaced by my log4j2 logging.
 */
@Configuration
public class RequestLogging extends CommonsRequestLoggingFilter{

    @Override
    protected String createMessage(HttpServletRequest request, String prefix, String suffix){
        String loggingMsg = super.createMessage(request, prefix, suffix);

        String safeLoggingMessage = loggingMsg==null?null:loggingMsg.replaceFirst("Signature=.*?&","Signature=hided");
        return safeLoggingMessage;
    }
    //https://www.javadevjournal.com/spring/log-incoming-requests-spring/
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {

        //loggingFilter.setIncludeQueryString(false); //because we hope DON'T print signature - we have printed the url(with signature) in beforeRequest
        CommonsRequestLoggingFilter filter = new RequestLogging();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(20000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("track request - ");
        return filter;
    }
}
