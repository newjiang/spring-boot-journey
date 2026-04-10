package org.newjiang.springboot.listener.advancedfeatures;

import org.newjiang.springboot.listener.advancedfeatures.event.ChainBEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-04-10
 */
@Component
public class ChainBListener {
    @EventListener
    public void onEvent(ChainBEvent event) {
        System.out.println(event);
    }
}
