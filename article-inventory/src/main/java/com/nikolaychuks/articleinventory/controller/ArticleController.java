package com.nikolaychuks.articleinventory.controller;

import com.nikolaychuks.articleinventory.model.Article;
import com.nikolaychuks.articleinventory.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService service;

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(service.findArticleById(id));
    }

    @GetMapping
    public List<Article> getAllArticles(){
        return service.findAll();
    }

    @PostMapping()
    public ResponseEntity<Article> createArticle(@RequestBody Article article){
        return ResponseEntity.ok(service.createArticle(article));
    }
}
