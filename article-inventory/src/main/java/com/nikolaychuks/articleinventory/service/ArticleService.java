package com.nikolaychuks.articleinventory.service;

import com.nikolaychuks.articleinventory.dto.ArticleToReduceDto;
import com.nikolaychuks.articleinventory.exceptions.ArticleNotFoundException;
import com.nikolaychuks.articleinventory.model.Article;
import com.nikolaychuks.articleinventory.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

    public Article findArticleById(Long id) {
        log.info("Finding article by id: {}", id);
        return repository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    public Article createArticle(Article article) {
        log.info("Creating article with title: {}", article.getTitle());
        return repository.save(article);
    }

    public List<Article> findAll() {
        Pageable limitArticles = PageRequest.of(0, 100);
        return repository.findAll(limitArticles).toList();
    }

    @Transactional
    public Article reduceArticle(ArticleToReduceDto article) {
        log.info("Reducing quantity of article with id: {}, for: {}", article.getArticleId(), article.getNumberOfArticles());

        Article existingArticle = repository.findById(article.getArticleId()).orElseThrow(() -> new ArticleNotFoundException(article.getArticleId()));
        return updateQuantity(existingArticle, -article.getNumberOfArticles());
    }

    private Article updateQuantity(Article article, Long quantityToAdd) {
        article.setQuantity(article.getQuantity() + quantityToAdd);
        return article;
    }
}
