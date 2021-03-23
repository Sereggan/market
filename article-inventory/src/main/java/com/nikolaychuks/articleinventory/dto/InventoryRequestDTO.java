package com.nikolaychuks.articleinventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InventoryRequestDTO {

    private List<ArticleDto> articles;
}
