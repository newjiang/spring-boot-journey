package org.newjiang.springboot.listener.designpattern;

import org.newjiang.springboot.listener.designpattern.event.WeatherEvent;

/**
 * 天气监听器
 *
 * @author newjiang
 * @since 2026-04-09
 */
public interface WeatherListener {
    /**
     * 监听添加事件
     *
     * @param event 事件
     */
    void onWeatherEvent(WeatherEvent event);
}
