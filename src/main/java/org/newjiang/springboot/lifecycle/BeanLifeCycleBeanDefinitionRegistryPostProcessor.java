package org.newjiang.springboot.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

import java.util.Iterator;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-04-10
 */
public class BeanLifeCycleBeanDefinitionRegistryPostProcessor
        implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE; // 优先级设为最高，确保最早执行
    }

    /**
     * BeanDefinition 注册完成后、实例化前执行
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (String beanName : registry.getBeanDefinitionNames()) {
            // 对应BeanLifeCycleConfiguration使用@Bean注解标记的方法名称
            if ("getBeanLifeCycle".equals(beanName)) {
                System.out.println("BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry >>> " + beanName);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        while (beanNamesIterator.hasNext()) {
            // 对应BeanLifeCycleConfiguration使用@Bean注解标记的方法名称
            String beanName = beanNamesIterator.next();
            if ("getBeanLifeCycle".equals(beanName)) {
                BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
                String beanClassName = definition.getBeanClassName();
                System.out.println("BeanDefinitionRegistryPostProcessor.postProcessBeanFactory >>> " + beanName);
            }
        }
    }
}