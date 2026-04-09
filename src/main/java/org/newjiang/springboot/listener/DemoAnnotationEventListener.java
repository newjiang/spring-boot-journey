package org.newjiang.springboot.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
public class DemoAnnotationEventListener {
    @EventListener
    public void handleMyEvent(ApplicationStartedEvent event) {
        System.out.println("hello DemoAnnotationEventListener, 处理事件: " + event);
    }
}
