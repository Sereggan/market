package com.nikolaychuks.articleinventory.service;

import com.nikolaychuks.articleinventory.exceptions.ArticleNotFoundException;
import com.nikolaychuks.articleinventory.model.Article;
import com.nikolaychuks.articleinventory.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Article> findAll() {
        Pageable limitArticles = PageRequest.of(0,100);
        return repository.findAll(limitArticles).toList();
    }
}
