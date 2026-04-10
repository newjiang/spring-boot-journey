package org.newjiang.springboot.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自定义BeanPostProcessor
 *
 * @author newjiang
 * @since 2026-04-10
 */
@Component
public class BeanLifeCycleBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("BeanPostProcessor：初始化前增强");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("BeanPostProcessor：初始化后增强");
        }
        return bean;
    }
}
