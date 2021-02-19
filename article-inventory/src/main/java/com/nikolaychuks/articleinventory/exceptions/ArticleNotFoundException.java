package com.nikolaychuks.articleinventory.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleNotFoundException extends RuntimeException {
    private Long id;
}
