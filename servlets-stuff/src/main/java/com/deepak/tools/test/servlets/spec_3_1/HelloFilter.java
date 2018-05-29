package com.deepak.tools.test.servlets.spec_3_1;

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

@WebFilter("/hello")
public class HelloFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(HelloFilter.class);

    @Override
    public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
        servletResponse
         .getWriter().write("filtering ");
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