package com.nikolaychuks.articleinventory.service;

import com.nikolaychuks.articleinventory.dto.ArticleDto;
import com.nikolaychuks.articleinventory.exceptions.ArticleNotFoundException;
import com.nikolaychuks.articleinventory.model.Article;
import com.nikolaychuks.articleinventory.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

    public Article findArticleById(String id) {
        log.info("Finding article by id: {}", id);
        return repository.findById(Long.parseLong(id)).orElseThrow(() -> new ArticleNotFoundException(id));
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
    public boolean deductInventory(List<ArticleDto> articleDtos) {
        return articleDtos.stream().anyMatch(articleDto -> deductArticle(articleDto.getId(), articleDto.getQuantity()));
    }

    private boolean deductArticle(String articleId, Long quantity) {
        Article article = repository.findById(Long.parseLong(articleId)).orElseThrow(() -> new ArticleNotFoundException(articleId));

        if (article.getQuantity() - quantity >= 0) {
            article.setQuantity(article.getQuantity() - quantity);
            return true;
        } else {
            return false;
        }
    }
}
