package com.nikolaychuks.orderservice.controller;

import com.nikolaychuks.orderservice.dto.OrderDto;
import com.nikolaychuks.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity createOrder(@RequestBody OrderDto order){
        orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
