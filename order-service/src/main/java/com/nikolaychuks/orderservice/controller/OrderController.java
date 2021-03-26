package com.nikolaychuks.orderservice.controller;

import com.nikolaychuks.orderservice.dto.OrderDto;
import com.nikolaychuks.orderservice.service.internal.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping()
    public ResponseEntity createOrder(@RequestBody OrderDto order) {
        orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
