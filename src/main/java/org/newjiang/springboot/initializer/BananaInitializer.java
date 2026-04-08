package org.newjiang.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Order(2)
public class BananaInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Map<String, Object> map = new HashMap<>();
        map.put("bananaInitializer", "香蕉初始化器");
        MapPropertySource mapPropertySource = new MapPropertySource("bananaInitializer", map);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("run bananaInitializer");
    }
}
