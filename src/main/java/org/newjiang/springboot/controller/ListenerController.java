package org.newjiang.springboot.controller;

import org.newjiang.springboot.listener.designpattern.listener.WeatherRunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/test")
    public Object test() {
        weatherRunListener.rain();
        weatherRunListener.snow();
        return "执行成功，详情见控制台打印的日志";
    }
}
