package com.nikolaychuks.articleinventory.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ArticleToReduceDto {

    @NotNull
    private Long articleId;
    @Positive
    private Long numberOfArticles = 0L;
}
