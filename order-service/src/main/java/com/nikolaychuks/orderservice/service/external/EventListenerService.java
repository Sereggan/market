package com.nikolaychuks.orderservice.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolaychuks.orderservice.enums.OrderStatus;
import com.nikolaychuks.orderservice.message.Message;
import com.nikolaychuks.orderservice.model.Order;
import com.nikolaychuks.orderservice.service.internal.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventListenerService {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    private final String ORDER_CONFIRMED_TOPIC = "order_confirmed";
    private final String ORDER_COMPLETED_TOPIC = "order_completed";

    @KafkaListener(topics = ORDER_CONFIRMED_TOPIC)
    public void consumeOrderConfirmed(Message<String> messageJson) throws JsonProcessingException {

        Order order = objectMapper.readValue(messageJson.getData(), Order.class);

        log.info("Received message that order confirmed: {}", order.getOrderId());

        orderService.changeOrderStatus(order.getOrderId(), OrderStatus.ORDER_CONFIRMED);
    }

    @KafkaListener(topics = ORDER_COMPLETED_TOPIC)
    public void consumeOrderCompleted(Message<String> messageJson) throws JsonProcessingException {

        Long orderId = Long.parseLong(objectMapper.readValue(messageJson.getData(),String.class));

        log.info("Received message that order completed: {}", orderId);

        orderService.changeOrderStatus(orderId, OrderStatus.ORDER_COMPLETED);
    }
}
