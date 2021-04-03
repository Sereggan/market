package com.nikolaychuks.orderservice.dto;

import com.nikolaychuks.orderservice.enums.OrderStatus;
import com.nikolaychuks.orderservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long userId;
    private Long price;
    @NotEmpty
    private List<ArticleDto> articles;

    public static Order toOrder(OrderDto orderDto) {
        return Order.builder()
                .orderStatus(OrderStatus.ORDER_CREATED)
                .price(orderDto.getPrice())
                .userId(orderDto.getUserId())
                .build();
    }

}
