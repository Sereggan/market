package com.nikolaychuks.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private String userId;
    private Long price;
    private List<ArticleDto> articles;
}
