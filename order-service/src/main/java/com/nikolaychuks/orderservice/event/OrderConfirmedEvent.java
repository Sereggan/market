package com.nikolaychuks.orderservice.event;

import com.nikolaychuks.orderservice.model.Order;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderConfirmedEvent {

    private Long id;
    private Long userId;
    private Long price;

    public static OrderConfirmedEvent toOrderConfirmedEvent(Order order) {
        return OrderConfirmedEvent.builder().id(order.getOrderId())
                .price(order.getPrice())
                .userId(order.getUserId())
                .build();
    }
}
