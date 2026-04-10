package org.newjiang.springboot.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-04-10
 */
@Configuration
@ComponentScan("org.newjiang.springboot.lifecycle")
public class BeanLifeCycleConfiguration {
    @Bean
    public BeanLifeCycleBeanDefinitionRegistryPostProcessor getBeanLifeCycleBeanDefinitionRegistryPostProcessor() {
        return new BeanLifeCycleBeanDefinitionRegistryPostProcessor();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanLifeCycle getBeanLifeCycle() {
        return new BeanLifeCycle();
    }
}
