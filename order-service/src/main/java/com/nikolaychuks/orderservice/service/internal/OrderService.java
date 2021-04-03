package com.nikolaychuks.orderservice.service.internal;

import com.nikolaychuks.orderservice.dto.OrderDto;
import com.nikolaychuks.orderservice.enums.OrderStatus;
import com.nikolaychuks.orderservice.event.OrderCreatedEvent;
import com.nikolaychuks.orderservice.exceptions.OrderNotFoundException;
import com.nikolaychuks.orderservice.message.Message;
import com.nikolaychuks.orderservice.model.Order;
import com.nikolaychuks.orderservice.repository.OrderRepository;
import com.nikolaychuks.orderservice.service.external.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final EventService eventService;

    private final String ORDER_CREATED_TOPIC = "order_created";

    public Order findById(String orderId) {
        return orderRepository.findById(Long.parseLong(orderId)).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public Order createOrder(OrderDto orderDto) {
        Order order = orderRepository.save(OrderDto.toOrder(orderDto));

        Message<OrderCreatedEvent> message = new Message();
        message.setData(OrderCreatedEvent.toOrderEvent(orderDto, order.getOrderId()));

        eventService.sendMessage(ORDER_CREATED_TOPIC, message);

        return order;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId.toString()));
        order.setOrderStatus(orderStatus);
        orderRepository.saveAndFlush(order);
        log.info("Changed order status, orderId: {}, new status: {}", order.getOrderId(), orderStatus);
        return order;
    }
}
