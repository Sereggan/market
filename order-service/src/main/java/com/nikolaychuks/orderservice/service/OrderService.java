package com.nikolaychuks.orderservice.service;

import com.nikolaychuks.orderservice.dto.OrderDto;
import com.nikolaychuks.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final EventService eventService;

    public void createOrder(OrderDto order) {
        eventService.createArticlesChangedMessage(order.getArticles());

    }
}
