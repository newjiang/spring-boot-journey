package org.newjiang.springboot.listener.advancedfeatures.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单事件
 *
 * @author newjiang
 * @since 2026-04-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String priority;
    private String category;
}
