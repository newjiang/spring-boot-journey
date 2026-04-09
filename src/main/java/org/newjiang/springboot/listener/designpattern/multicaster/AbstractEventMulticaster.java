package org.newjiang.springboot.listener.designpattern.multicaster;

import org.newjiang.springboot.listener.designpattern.EventMulticaster;
import org.newjiang.springboot.listener.designpattern.event.WeatherEvent;
import org.newjiang.springboot.listener.designpattern.WeatherListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象事件多播器
 *
 * @author newjiang
 * @since 2026-04-09
 */
@Component
public abstract class AbstractEventMulticaster implements EventMulticaster {

    @Autowired
    private List<WeatherListener> listenerList = new ArrayList<>();

    @Override
    public void multicastEvent(WeatherEvent event) {
        doStart();
        listenerList.forEach(e -> e.onWeatherEvent(event));
        doEnd();
    }

    @Override
    public void addListener(WeatherListener weatherListener) {
        listenerList.add(weatherListener);
    }

    @Override
    public void removeListener(WeatherListener weatherListener) {
        listenerList.remove(weatherListener);
    }

    /**
     * 开始
     */
    abstract void doStart();

    /**
     * 结束
     */
    abstract void doEnd();

}
