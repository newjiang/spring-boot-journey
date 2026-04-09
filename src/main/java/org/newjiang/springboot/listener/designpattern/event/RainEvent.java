package org.newjiang.springboot.listener.designpattern.event;

/**
 * 下雨天事件
 *
 * @author newjiang
 * @since 2026-04-09
 */
public class RainEvent extends WeatherEvent {
    @Override
    public String getWeather() {
        return "下雨";
    }
}
