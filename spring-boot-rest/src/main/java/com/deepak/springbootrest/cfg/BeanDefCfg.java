package com.deepak.springbootrest.cfg;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDefCfg implements BeanFactoryPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BeanDefCfg.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        StringBuilder myPkgSb = new StringBuilder(100);
        StringBuilder springSb = new StringBuilder(1000);
        try {
            myPkgSb.append("Bean defs available: ");
            myPkgSb.append(beanDefinitionNames.length);
            myPkgSb.append(", beans: ");
            for (String beanName : beanDefinitionNames) {
                if (beanName.startsWith("com.deepak.")) {
                    myPkgSb.append(StringUtils.substringAfterLast(beanName, "."));
                    myPkgSb.append(", ");
                } else if (beanName.startsWith("org.springframework")) {
                    springSb.append(beanName);
                    springSb.append(", ");
                } else {
                    myPkgSb.append(beanName);
                    myPkgSb.append(", ");
                }
            }
            if (springSb.charAt(springSb.length() - 2) == ',') {
                springSb.delete(springSb.length() - 2, springSb.length() - 1);
            }
            logger.info("{}{}", myPkgSb, springSb);
        } catch (Exception e) {
            logger.info("StackTrace:", e);
        }
    }

}
