package org.newjiang.springboot.listener.advancedfeatures;

import org.newjiang.springboot.listener.advancedfeatures.event.OrderEvent;
import org.newjiang.springboot.listener.advancedfeatures.event.PaymentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-04-10
 */
@Component
public class FilteredEventListener {
    /**
     * 使用 SpEL 表达式过滤
     */
    @EventListener(condition = "#event.priority == 'HIGH' and #event.category == 'ORDER'")
    public void handleHighPriorityOrder(OrderEvent event) {
        System.out.println("处理高优先级订单事件:" + event);
    }

    /**
     * 使用多个条件
     */
    @EventListener(condition = "#event.amount > 1000")
    public void handleLargeAmount(PaymentEvent event) {
        System.out.println(("处理大额支付: " + event));
    }
}