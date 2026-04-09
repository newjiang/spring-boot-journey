package org.newjiang.springboot.listener.designpattern;

import org.newjiang.springboot.listener.designpattern.event.RainEvent;
import org.newjiang.springboot.listener.designpattern.event.SnowEvent;
import org.newjiang.springboot.listener.designpattern.listener.RainListener;
import org.newjiang.springboot.listener.designpattern.listener.SnowListener;
import org.newjiang.springboot.listener.designpattern.multicaster.WeatherEventMulticaster;

public class Application {
    public static void main(String[] args) {
        WeatherEventMulticaster eventMulticaster = new WeatherEventMulticaster();
        RainListener rainListener = new RainListener();
        SnowListener snowListener = new SnowListener();
        eventMulticaster.addListener(rainListener);
        eventMulticaster.addListener(snowListener);
        eventMulticaster.multicastEvent(new SnowEvent());
        eventMulticaster.multicastEvent(new RainEvent());
        eventMulticaster.removeListener(rainListener);
        eventMulticaster.multicastEvent(new SnowEvent());
        eventMulticaster.multicastEvent(new RainEvent());
    }
}
