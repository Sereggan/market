package com.nikolaychuks.articleinventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class ArticleToReduceDto {

    @NotNull
    private Long articleId;
    @Positive
    private Long numberOfArticles = 0L;
}
