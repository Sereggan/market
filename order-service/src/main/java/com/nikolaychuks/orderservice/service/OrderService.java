package com.nikolaychuks.orderservice.service;

import com.nikolaychuks.orderservice.dto.OrderCreatedEvent;
import com.nikolaychuks.orderservice.dto.OrderDto;
import com.nikolaychuks.orderservice.enums.OrderStatus;
import com.nikolaychuks.orderservice.exceptions.OrderNotFoundException;
import com.nikolaychuks.orderservice.model.Order;
import com.nikolaychuks.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final EventService eventService;

    public Order findById(String orderId){
        return orderRepository.findById(orderId).orElse(Order.builder().build());
    }

    public void createOrder(OrderDto orderDto) {
        Order order = orderRepository.save(OrderDto.toOrder(orderDto));

        eventService.createOrderCreatedMessage(OrderCreatedEvent.toOrderEvent(orderDto,order.getId().toString()));
    }
}
