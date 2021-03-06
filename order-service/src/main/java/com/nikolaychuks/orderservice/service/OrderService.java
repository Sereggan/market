package com.nikolaychuks.orderservice.service;

import com.nikolaychuks.orderservice.model.Order;
import com.nikolaychuks.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(Order order) {
    }
}
