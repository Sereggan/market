package com.nikolaychuks.orderservice.event;

import com.nikolaychuks.orderservice.dto.ArticleDto;
import com.nikolaychuks.orderservice.dto.OrderDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderCreatedEvent {

    private String orderId;
    private List<ArticleDto> articles;
    private Long price;
    private Long userId;

    public static OrderCreatedEvent toOrderEvent(OrderDto orderDto, String id) {
        return OrderCreatedEvent.builder()
                .orderId(id)
                .articles(orderDto.getArticles())
                .price(orderDto.getPrice())
                .userId(orderDto.getUserId())
                .build();
    }
}
