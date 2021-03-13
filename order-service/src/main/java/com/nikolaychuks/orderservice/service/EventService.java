package com.nikolaychuks.orderservice.service;

import com.nikolaychuks.orderservice.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    public void createArticlesChangedMessage(List<ArticleDto> articles) {
        // posts to kafka
    }
}
