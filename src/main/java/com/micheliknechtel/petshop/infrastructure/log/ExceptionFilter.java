package com.micheliknechtel.petshop.infrastructure.log;

import jdk.nashorn.internal.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class ExceptionFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Throwable e) {
            logger.error(new StructuredLog(Action.USER, e.getMessage()).toJson());
        }
    }

    @Override
    public void destroy() {

    }
}
