package com.nikolaychuks.articleinventory.service.external;

import com.nikolaychuks.articleinventory.events.OrderConfirmedEvent;
import com.nikolaychuks.articleinventory.events.OrderCreatedEvent;
import com.nikolaychuks.articleinventory.exceptions.CouldNotDeductArticlesException;
import com.nikolaychuks.articleinventory.message.Message;
import com.nikolaychuks.articleinventory.service.internal.ArticleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Slf4j
@Service
public class EventService {

    private final ArticleService articleService;
    private final KafkaTemplate<String, Message> kafkaTemplate;

    private final String ORDER_CONFIRMED_TOPIC = "order_confirmed";
    private final String ORDER_CREATED_TOPIC = "order_created";

    public void sendMessage(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = ORDER_CREATED_TOPIC)
    private void consumeOrderCreated(Message<Object> messageJson) {
        OrderCreatedEvent orderCreatedEvent = (OrderCreatedEvent) messageJson.getData();

        log.info("Receiver OrderCreated Event with order id: {}", orderCreatedEvent.getOrderId());
        try {
            articleService.deductInventory(orderCreatedEvent.getArticles());
            messageJson.setData(OrderConfirmedEvent.toOrderConfirmed(orderCreatedEvent));
            log.info("Sending OrderConfirmed Event with order id: {}", orderCreatedEvent.getOrderId());

            sendMessage(ORDER_CONFIRMED_TOPIC, messageJson);
        } catch (CouldNotDeductArticlesException e) {
            log.info("Couldn't confirm order with order id: {}", orderCreatedEvent.getOrderId());
        }
    }
}
