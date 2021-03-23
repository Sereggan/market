package com.nikolaychuks.orderservice.service;

import com.nikolaychuks.orderservice.dto.OrderConfirmedEvent;
import com.nikolaychuks.orderservice.dto.OrderCreatedEvent;
import com.nikolaychuks.orderservice.enums.OrderStatus;
import com.nikolaychuks.orderservice.exceptions.OrderNotFoundException;
import com.nikolaychuks.orderservice.model.Order;
import com.nikolaychuks.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderRepository orderRepository;

    public void createOrderCreatedMessage(OrderCreatedEvent order) {
        kafkaTemplate.send("order_created", order);
    }

    @KafkaListener(topics = "order_confirmed")
    public void consumeOrderConfirmed(String orderId) {
        changeOrderStatus(orderId, OrderStatus.ORDER_CONFIRMED);
        createOrderConfirmedMessage(orderId);
    }

    private void createOrderConfirmedMessage(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        kafkaTemplate.send("order_confirmed", OrderConfirmedEvent.toOrderConfirmedEvent(order));
    }

    private void changeOrderStatus(String orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        order.setOrderStatus(orderStatus);
    }
}
