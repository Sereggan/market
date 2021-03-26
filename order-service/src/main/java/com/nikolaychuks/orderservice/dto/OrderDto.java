package com.nikolaychuks.orderservice.dto;

import com.nikolaychuks.orderservice.enums.OrderStatus;
import com.nikolaychuks.orderservice.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long userId;
    private Long price;
    private List<ArticleDto> articles;

    public static Order toOrder(OrderDto orderDto) {
        return Order.builder()
                .orderStatus(OrderStatus.ORDER_CREATED)
                .price(orderDto.getPrice())
                .userId(orderDto.getUserId())
                .build();
    }

}
