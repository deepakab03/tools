package com.deepak.springbootrest.cond;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.deepak.springbootrest.cfg.BeanDefCfg;
import com.deepak.springbootrest.cond.conditions.CondExists;
import com.deepak.springbootrest.cond.conditions.CondNotExists;

@Configuration
@Import(BeanDefCfg.class)
public class ConditionalTestConfig {
    private static final Logger logger = LoggerFactory.getLogger(ConditionalTestConfig.class);

    @Bean
    @Conditional(CondExists.class)
    public HumptyDumptyService service1() {
        logger.info("Created service1");
        return new HumptyDumptyServiceImpl1();
    }

    @Bean
    @Conditional(CondNotExists.class)
    public HumptyDumptyService service2() {
        logger.info("Created service2");
        return new HumptyDumptyServiceImpl2();
    }
}
