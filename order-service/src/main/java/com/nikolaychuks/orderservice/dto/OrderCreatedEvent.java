package com.nikolaychuks.orderservice.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderCreatedEvent {

    private String id;
    private List<ArticleDto> articles;

    public static OrderCreatedEvent toOrderEvent(OrderDto orderDto, String id){
        return OrderCreatedEvent.builder()
                .id(id)
                .articles(orderDto.getArticles())
                .build();
    }
}
