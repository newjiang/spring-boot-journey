package org.newjiang.springboot.lifecycle;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-04-10
 */
public class BeanLifeCycleApplication {
    public static void main(String[] args) {
        // 这里手动处理一下日志打印，让打印内容更加的清晰
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("org.springframework");
        logger.setLevel(Level.toLevel("ERROR"));

        System.out.println("spring bean cycle start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 1.启动容器（ApplicationContext）
        try (AnnotationConfigApplicationContext context
                     = new AnnotationConfigApplicationContext(BeanLifeCycleConfiguration.class)) {
            // 2.获取Bean并使用
            BeanLifeCycle bean = context.getBean(BeanLifeCycle.class);
            bean.doWork();
        }
        // 3.容器自动关闭（触发销毁逻辑）
        System.out.println("spring bean cycle end   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
