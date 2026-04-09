package org.newjiang.springboot.listener.designpattern.event;

/**
 * 下雪天事件
 *
 * @author newjiang
 * @since 2026-04-09
 */
public class SnowEvent extends WeatherEvent {
    @Override
    public String getWeather() {
        return "下雪";
    }
}
