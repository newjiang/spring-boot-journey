package org.newjiang.springboot.listener.designpattern.multicaster;

import org.springframework.stereotype.Component;

/**
 * 天气事件多播器
 *
 * @author newjiang
 * @since 2026-04-09
 */
@Component
public class WeatherEventMulticaster extends AbstractEventMulticaster {

    @Override
    void doStart() {
        System.out.println("begin broadcast weather event");
    }

    @Override
    void doEnd() {
        System.out.println("end broadcast weather event");
    }
}
