package org.newjiang.springboot.listener.advancedfeatures;

import org.newjiang.springboot.listener.advancedfeatures.event.ChainAEvent;
import org.newjiang.springboot.listener.advancedfeatures.event.ChainBEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-04-10
 */
@Component
public class ChainAListener {
    @Autowired
    private ApplicationEventPublisher publisher;

    @EventListener
    public void onEvent(ChainAEvent event) {
        System.out.println(event);
        publisher.publishEvent(new ChainBEvent("我是Chain B Event，来自：" + event));
    }
}
