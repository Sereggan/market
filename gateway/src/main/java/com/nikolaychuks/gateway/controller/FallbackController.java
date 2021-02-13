package com.nikolaychuks.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/quizFallBack")
    public Mono<String> quizServiceFallBack(){
        return Mono.just("Quiz Service is taking too long to respond or down. Please try again later");
    }

    @RequestMapping("/motivationFallBack")
    public Mono<String> motivationServiceFallBack(){
        return Mono.just("Motivation Service is taking too long to respond or down. Please try again later");
    }
}
