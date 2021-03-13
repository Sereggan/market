package com.nikolaychuks.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private String userId;
    private Long price;
    private List<ArticleDto> articles;
}
