package com.nikolaychuks.articleinventory.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolaychuks.articleinventory.events.OrderConfirmedEvent;
import com.nikolaychuks.articleinventory.events.OrderCreatedEvent;
import com.nikolaychuks.articleinventory.exceptions.CouldNotDeductArticlesException;
import com.nikolaychuks.articleinventory.message.Message;
import com.nikolaychuks.articleinventory.service.internal.ArticleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final ObjectMapper objectMapper;

    private final String ORDER_CONFIRMED_TOPIC = "order_confirmed";
    private final String ORDER_CREATED_TOPIC = "order_created";

    public void sendMessage(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = ORDER_CREATED_TOPIC)
    private void consumeOrderCreated(Message<String> messageJson) {
        OrderCreatedEvent orderCreatedEvent = null;
        try {
            orderCreatedEvent = objectMapper.readValue(messageJson.getData(), OrderCreatedEvent.class);
        } catch (JsonProcessingException e) {
            log.info("Could not convert event");
        }

        log.info("Receiver OrderCreated Event with order id: {}", orderCreatedEvent.getOrderId());
        try {
            articleService.deductInventory(orderCreatedEvent.getArticles());
            Message<String> orderConfirmedEventMessage = new Message<>();
            try {
                orderConfirmedEventMessage.setData(objectMapper.writeValueAsString(OrderConfirmedEvent.toOrderConfirmed(orderCreatedEvent)));
            } catch (JsonProcessingException e) {
                log.info("Could not write event");
            }

            log.info("Sending OrderConfirmed Event with order id: {}", orderConfirmedEventMessage.getData());

            sendMessage(ORDER_CONFIRMED_TOPIC, orderConfirmedEventMessage);
        } catch (CouldNotDeductArticlesException e) {
            log.info("Couldn't confirm order with order id: {}", orderCreatedEvent.getOrderId());
        }
    }


}
