package com.nikolaychuks.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleDto {
    String id;
    Long quantity;
}
