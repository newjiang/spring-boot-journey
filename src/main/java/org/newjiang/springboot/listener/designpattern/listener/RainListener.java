package org.newjiang.springboot.listener.designpattern.listener;

import org.newjiang.springboot.listener.designpattern.event.WeatherEvent;
import org.newjiang.springboot.listener.designpattern.WeatherListener;
import org.newjiang.springboot.listener.designpattern.event.RainEvent;
import org.springframework.stereotype.Component;

/**
 * 下雨天事件监听器
 *
 * @author newjiang
 * @since 2026-04-09
 */
@Component
public class RainListener implements WeatherListener {
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof RainEvent) {
            System.out.println("hello " + event.getWeather());
        }
    }
}
