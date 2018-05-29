package com.deepak.tools.test.spring.session.servlet_spec_rel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class SpringSessionServletSpecServletContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(SpringSessionServletSpecServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("CONTEXT INIT!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("CONTEXT DESTROYED!");
    }

}
