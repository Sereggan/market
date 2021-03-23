package com.nikolaychuks.orderservice.dto;

import com.nikolaychuks.orderservice.model.Order;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderConfirmedEvent {

    private Long id;
    private String userId;
    private Long price;

    public static OrderConfirmedEvent toOrderConfirmedEvent(Order order){
        return OrderConfirmedEvent.builder().id(order.getId())
                .price(order.getPrice())
                .userId(order.getUserId())
                .build();
    }
}
