package org.newjiang.springboot;

import org.newjiang.springboot.initializer.BananaInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author newjiang
 * @since 2026-04-09
 */
@SpringBootApplication
public class JourneyApplication {
    public static void main(String[] args) {
         SpringApplication.run(JourneyApplication.class, args);

        // 代码注册初始化器-编程式注册
        // SpringApplication springApplication = new SpringApplication(JourneyApplication.class);
        // springApplication.addInitializers(new BananaInitializer());
        // springApplication.run(args);
    }
}
