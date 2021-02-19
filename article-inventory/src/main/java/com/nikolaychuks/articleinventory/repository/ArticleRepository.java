package com.nikolaychuks.articleinventory.repository;

import com.nikolaychuks.articleinventory.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
