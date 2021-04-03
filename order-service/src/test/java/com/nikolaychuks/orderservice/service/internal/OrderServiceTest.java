package com.nikolaychuks.orderservice.service.internal;

import com.nikolaychuks.orderservice.dto.OrderDto;
import com.nikolaychuks.orderservice.enums.OrderStatus;
import com.nikolaychuks.orderservice.model.Order;
import com.nikolaychuks.orderservice.repository.OrderRepository;
import com.nikolaychuks.orderservice.service.external.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private EventService eventService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldCreateOrder() {
        Order orderWithStatusCreated = Order.builder().price(100L).orderStatus(OrderStatus.ORDER_CREATED).orderId(1L).userId(1L).build();

        when(orderRepository.save(any())).thenReturn(orderWithStatusCreated);

        assertEquals(orderService.createOrder(OrderDto.builder().build()).getOrderStatus(), OrderStatus.ORDER_CREATED);
    }

    @Test
    void shouldChangeOrderStatusToConfirmed() {
        Order orderWithStatusCreated = Order.builder().price(100L).orderStatus(OrderStatus.ORDER_CREATED).orderId(1L).userId(1L).build();

        when(orderRepository.findById(any())).thenReturn(Optional.of(orderWithStatusCreated));

        assertEquals(OrderStatus.ORDER_CONFIRMED, orderService.changeOrderStatus(1L, OrderStatus.ORDER_CONFIRMED).getOrderStatus());
    }
}
