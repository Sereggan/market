package com.nikolaychuks.paymentservice.events;

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

}
