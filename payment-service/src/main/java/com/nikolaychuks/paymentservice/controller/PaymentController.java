package com.nikolaychuks.paymentservice.controller;

import com.nikolaychuks.paymentservice.model.PaymentInfo;
import com.nikolaychuks.paymentservice.service.internal.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInfo> getPaymentInfo(@PathVariable String id) {
        return ResponseEntity.ok(service.findPaymentInfoById(id));
    }

    @PostMapping()
    public ResponseEntity createPaymentInfo(@RequestBody PaymentInfo paymentInfo) {
        return ResponseEntity.ok(service.createPaymentInfo(paymentInfo));
    }
}
