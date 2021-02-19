package com.nikolaychuks.articleinventory.controller.advisor;

import com.nikolaychuks.articleinventory.exceptions.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = ArticleNotFoundException.class)
    protected ResponseEntity<Object> handleArticleNotFoundException(ArticleNotFoundException exception){
        log.info("Could not find Article, id: {}", exception.getId());

        return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
    }
}
