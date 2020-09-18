package com.my.springcloud.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class ConfigUtils implements ApplicationContextAware {

    private static Environment env;
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigUtils.applicationContext = applicationContext;
        env = applicationContext.getBean(Environment.class);
    }

    public static String getValue(String key) {
        return env.getProperty(key);
    }
    public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }
}
