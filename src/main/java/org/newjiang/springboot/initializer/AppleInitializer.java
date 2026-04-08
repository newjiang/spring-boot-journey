package org.newjiang.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.LinkedHashMap;
import java.util.Map;

@Order(1)
public class AppleInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Map<String, Object> source = new LinkedHashMap<>();
        source.put("appleInitializer", "苹果初始化器");
        MapPropertySource mapPropertySource = new MapPropertySource("appleInitializer", source);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("run appleInitializer");
    }
}
