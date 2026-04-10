package org.newjiang.springboot.controller;

import org.newjiang.springboot.listener.advancedfeatures.event.*;
import org.newjiang.springboot.listener.designpattern.listener.WeatherRunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 监听器器测试类
 *
 * @author newjiang
 * @since 2026-04-09
 */
@RestController
@RequestMapping("/listener")
public class ListenerController {
    @Autowired
    private WeatherRunListener weatherRunListener;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/test")
    public Object test() {
        weatherRunListener.rain();
        weatherRunListener.snow();

        // 发布事件
        for (Object event : Arrays.asList(
                new OrderEvent("HIGH", "ORDER"),
                new OrderEvent("LOW", "ORDER"),
                new PaymentEvent(1),
                new PaymentEvent(1001),
                new ChainAEvent("我是事件链A"),
                new UserBaseEvent(),
                new UserCreatedEvent(),
                new UserDeletedEvent()
        )) {
            System.out.println(">>> publisher start publish event:" + event);
            publisher.publishEvent(event);
            System.out.println("<<< publisher completed publish event:" + event);
            System.out.println("----------------------------------------------");
        }
        return "执行成功，详情见控制台打印的日志";
    }
}
