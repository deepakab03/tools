package com.deepak.tools.test.spring.session.servlet_spec_rel;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/someService/sayHi")
public class SpringSessionServletSpecHelloFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SpringSessionServletSpecHelloFilter.class);

    @Override
    public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
        logger.info("Doing some filtering...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("FILTER INIT");
    }

    @Override
    public void destroy() {
        logger.info("FILTER DESTROY");
    }

}