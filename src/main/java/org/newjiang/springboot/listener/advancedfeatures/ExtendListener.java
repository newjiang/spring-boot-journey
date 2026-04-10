package org.newjiang.springboot.listener.advancedfeatures;

import org.newjiang.springboot.listener.advancedfeatures.event.UserBaseEvent;
import org.newjiang.springboot.listener.advancedfeatures.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-04-10
 */
@Component
public class ExtendListener {
    @EventListener
    public void handleAllUserEvents(UserBaseEvent event) {
        System.out.println("handleAllUserEvents handle " + event.getClass().getSimpleName());
    }

    @EventListener
    public void handleSpecificEvent(UserCreatedEvent event) {
        System.out.println("handleSpecificEvent handle " + event.getClass().getSimpleName());
    }
}
