package com.deepak.tools.test.spring.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Shows how spring sessions work and how servlet spec related webListeneres can be used with in embedded web
 * application with <code>ServletComponentScan</code> (not required for application published to external tomcat)
 * <p>
 * Extending <code>SpringBootServletInitializer</code> required to make the application work when it is to be deployed
 * to external Tomcat
 *
 * @author abrahd2
 */
@SpringBootApplication
@ServletComponentScan("com.deepak.tools.test.spring.session.servlet_spec_rel")
public class SessionApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SessionApplication.class, args);
    }

}
