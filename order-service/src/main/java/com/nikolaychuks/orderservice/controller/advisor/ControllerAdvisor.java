package com.nikolaychuks.orderservice.controller.advisor;

import com.nikolaychuks.orderservice.exceptions.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = OrderNotFoundException.class)
    protected ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception) {
        log.info("Could not find Order, id: {}", exception.getId());

        return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
    }


}
