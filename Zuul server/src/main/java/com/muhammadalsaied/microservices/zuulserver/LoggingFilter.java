package com.muhammadalsaied.microservices.zuulserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by @author Muhammed Alsaied On Oct 28, 2018
 * muhammadalsaied96@gmail.com
 */
@Component
public class LoggingFilter extends ZuulFilter {

    private static Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest reauest = RequestContext.getCurrentContext().getRequest();
        LOG.info("Request {} , and uri {}", reauest, reauest.getRequestURI());

        return null;
    }

}
