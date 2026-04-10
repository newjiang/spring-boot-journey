package org.newjiang.springboot.lifecycle;


import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Spring Bean的生命周期
 *
 * @author newjiang
 * @since 2026-04-10
 */
public class BeanLifeCycle implements InitializingBean, DisposableBean, BeanNameAware {
    /**
     * 阶段2：实例化（构造器）
     */
    public BeanLifeCycle() {
        System.out.println("创建阶段：1. 实例化：构造器执行");
    }

    /**
     * 阶段3：属性注入（@Autowired）
     */
    @Autowired
    public void setUserDao(BeanLifeCycleService beanLifeCycleService) {
        System.out.println("创建阶段：2. 属性注入：beanLifeCycleService已注入");
    }

    /**
     * 阶段4：Aware接口（BeanNameAware）
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("创建阶段：3. Aware接口：Bean名称为" + name);
    }

    /**
     * 阶段5：BeanPostProcessor前置处理（需自定义BeanPostProcessor）
     * 阶段6：初始化（@PostConstruct → InitializingBean → init-method）
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("创建阶段(初始化)：4. 初始化：@PostConstruct执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("创建阶段(初始化)：5. 初始化：InitializingBean执行");
    }

    /**
     * 需配置@Bean(initMethod = "initMethod")
     */
    public void initMethod() {
        System.out.println("创建阶段(初始化)：6. 初始化：init-method执行");
    }

    /**
     * 阶段7：BeanPostProcessor后置处理（需自定义BeanPostProcessor）
     * 阶段8：使用（假设被其他Bean调用）
     */
    public void doWork() {
        System.out.println("7. 使用：Bean正在工作");
    }

    /**
     * 阶段10：销毁（@PreDestroy → DisposableBean → destroy-method）
     */
    @PreDestroy
    public void preDestroy() {
        System.out.println("销毁阶段：8. 销毁：@PreDestroy执行");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁阶段：9. 销毁：DisposableBean执行");
    }

    /**
     * 需配置@Bean(destroyMethod = "destroyMethod")
     */
    public void destroyMethod() {
        System.out.println("销毁阶段：10. 销毁：destroy-method执行");
    }
}

