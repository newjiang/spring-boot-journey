package org.newjiang.springboot.listener.designpattern.listener;

import org.newjiang.springboot.listener.designpattern.WeatherListener;
import org.newjiang.springboot.listener.designpattern.event.SnowEvent;
import org.newjiang.springboot.listener.designpattern.event.WeatherEvent;
import org.springframework.stereotype.Component;

/**
 * 下雪天事件监听器
 *
 * @author newjiang
 * @since 2026-04-09
 */
@Component
public class SnowListener implements WeatherListener {
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof SnowEvent) {
            System.out.println("hello " + event.getWeather());
        }
    }
}
