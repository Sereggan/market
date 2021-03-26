package com.nikolaychuks.articleinventory.events;

import com.nikolaychuks.articleinventory.dto.ArticleDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderCreatedEvent {

    private Long orderId;
    private List<ArticleDto> articles;
    private Long price;
    private Long userId;
}
