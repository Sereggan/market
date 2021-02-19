package com.nikolaychuks.articleinventory.service;

import com.nikolaychuks.articleinventory.exceptions.ArticleNotFoundException;
import com.nikolaychuks.articleinventory.model.Article;
import com.nikolaychuks.articleinventory.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    
    private final ArticleRepository repository;

    public Article findArticleById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ArticleNotFoundException(id));
    }

    public Article createArticle(Article article) {
        return repository.save(article);
    }
}
