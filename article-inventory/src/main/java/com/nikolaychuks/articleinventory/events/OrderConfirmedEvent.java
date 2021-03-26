package com.nikolaychuks.articleinventory.events;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderConfirmedEvent {

    private Long orderId;
    private Long userId;
    private Long price;

    public static OrderConfirmedEvent toOrderConfirmed(OrderCreatedEvent orderCreatedEvent){
        return OrderConfirmedEvent.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .price(orderCreatedEvent.getPrice())
                .userId(orderCreatedEvent.getUserId())
                .build();
    }
}
