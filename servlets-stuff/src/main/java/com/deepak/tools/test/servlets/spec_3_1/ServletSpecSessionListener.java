package com.deepak.tools.test.servlets.spec_3_1;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * With spring-boot embedded tomctat requires addition of @ServletComponentScan to the Spring boot app annotated class
 * @author abrahd2
 *
 */
@WebListener
public class ServletSpecSessionListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(ServletSpecSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("SESSION CREATED!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("SESSION DESTROYED!");
    }

}
