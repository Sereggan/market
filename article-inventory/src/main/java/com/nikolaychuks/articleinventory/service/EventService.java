package com.nikolaychuks.articleinventory.service;

import com.nikolaychuks.articleinventory.dto.OrderCreatedEvent;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Service
public class EventService {

    private final ArticleService articleService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "order_created")
    public void consumeOrderCreated(OrderCreatedEvent orderCreatedEvent){
        if(articleService.deductInventory(orderCreatedEvent.getArticles())){
            kafkaTemplate.send("order_confirmed",orderCreatedEvent.getId().toString());
        };
    }

}
