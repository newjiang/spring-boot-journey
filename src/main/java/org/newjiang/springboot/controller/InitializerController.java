package org.newjiang.springboot.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 初始化器测试类
 *
 * @author newjiang
 * @since 2026-04-09
 */
@RestController
@RequestMapping("/initializer")
public class InitializerController implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/test")
    public Object test() {
        Map<Object, Object> result = new LinkedHashMap<>();
        result.put("appleInitializer", applicationContext.getEnvironment().getProperty("appleInitializer"));
        result.put("bananaInitializer", applicationContext.getEnvironment().getProperty("bananaInitializer"));
        result.put("cherryInitializer", applicationContext.getEnvironment().getProperty("cherryInitializer"));
        return result;
    }
}
