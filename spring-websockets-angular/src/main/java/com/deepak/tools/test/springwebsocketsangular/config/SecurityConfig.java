package com.deepak.tools.test.springwebsocketsangular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //TODO temp users for now
        manager.createUser(User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build());
        return manager;
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf()
        .disable()
        .authorizeRequests()
            .anyRequest()
            .permitAll();
        
//        http.sessionManagement()
//            .invalidSessionUrl("/login")
//            .maximumSessions(internalProps.getMaxSessions())
//                .sessionRegistry(sessionConfig.sessionRegistry());
        
    }
}
