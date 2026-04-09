package org.newjiang.springboot.listener.designpattern;

import org.newjiang.springboot.listener.designpattern.event.WeatherEvent;

public interface EventMulticaster {

    void multicastEvent(WeatherEvent event);

    void addListener(WeatherListener weatherListener);

    void removeListener(WeatherListener weatherListener);

}
