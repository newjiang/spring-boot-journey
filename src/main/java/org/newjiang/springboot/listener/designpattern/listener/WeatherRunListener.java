package org.newjiang.springboot.listener.designpattern.listener;

import org.newjiang.springboot.listener.designpattern.event.RainEvent;
import org.newjiang.springboot.listener.designpattern.event.SnowEvent;
import org.newjiang.springboot.listener.designpattern.multicaster.WeatherEventMulticaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 天气事件监听器
 *
 * @author newjiang
 * @since 2026-04-09
 */
@Component
public class WeatherRunListener {

    @Autowired
    private WeatherEventMulticaster eventMulticaster;

    /**
     * 下雪
     */
    public void snow() {
        eventMulticaster.multicastEvent(new SnowEvent());
    }

    /**
     * 下雨
     */
    public void rain() {
        eventMulticaster.multicastEvent(new RainEvent());
    }
}
