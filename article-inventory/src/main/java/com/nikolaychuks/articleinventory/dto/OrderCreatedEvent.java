package com.nikolaychuks.articleinventory.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderCreatedEvent {

    private Long id;
    private List<ArticleDto> articles;
}
