package com.nikolaychuks.paymentservice.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping
    public String kek(){
        kafkaTemplate.send("Topic",TestClass.builder().kek("KEK123").idk(123L).build());
        return "kek";
    }

    @Data
    @Builder
     static class TestClass {
        private String kek;
        private Long idk;
    }
}
