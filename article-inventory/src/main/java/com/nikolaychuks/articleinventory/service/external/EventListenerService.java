package com.nikolaychuks.articleinventory.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolaychuks.articleinventory.events.OrderConfirmedEvent;
import com.nikolaychuks.articleinventory.events.OrderCreatedEvent;
import com.nikolaychuks.articleinventory.exceptions.CouldNotDeductArticlesException;
import com.nikolaychuks.articleinventory.message.Message;
import com.nikolaychuks.articleinventory.service.internal.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventListenerService {
    private final ArticleService articleService;
    private final ObjectMapper mapper;
    private final EventService eventService;

    private final String ORDER_CONFIRMED_TOPIC = "order_confirmed";
    private final String ORDER_CREATED_TOPIC = "order_created";

    @SneakyThrows
    @KafkaListener(topics = ORDER_CREATED_TOPIC)
    private void consumeOrderCreated(Message<String> messageJson) {
        OrderCreatedEvent orderCreatedEvent = mapper.readValue(messageJson.getData(), OrderCreatedEvent.class);

        log.info("Receiver OrderCreated Event with order id: {}", orderCreatedEvent.getOrderId());
        try {
            articleService.deductInventory(orderCreatedEvent.getArticles());
            Message<String> orderConfirmedEventMessage = new Message<>();
            orderConfirmedEventMessage.setData(mapper.writeValueAsString(OrderConfirmedEvent.toOrderConfirmed(orderCreatedEvent)));

            log.info("Sending OrderConfirmed Event with order id: {}", orderConfirmedEventMessage.getData());

            eventService.sendMessage(ORDER_CONFIRMED_TOPIC, orderConfirmedEventMessage);
        } catch (CouldNotDeductArticlesException e) {
            log.info("Couldn't confirm order with order id: {}", orderCreatedEvent.getOrderId());
        }
    }
}
