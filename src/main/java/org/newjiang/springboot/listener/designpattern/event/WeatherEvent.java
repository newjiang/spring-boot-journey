package org.newjiang.springboot.listener.designpattern.event;

/**
 * 天气事件
 *
 * @author newjiang
 * @since 2026-04-09
 */
public abstract class WeatherEvent {
    /**
     * 获取天气
     *
     * @return 天气
     */
    public abstract String getWeather();
}
