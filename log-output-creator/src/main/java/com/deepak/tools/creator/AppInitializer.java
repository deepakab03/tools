package com.deepak.tools.creator;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Required though not specified in the spring boot docs, perhaps for tomcat 8.0 versions only.
 * @author abrahd2
 *
 */
public class AppInitializer extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
   
} 
