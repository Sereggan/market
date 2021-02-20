package com.nikolaychuks.cartservice.controller.advisor;

import com.nikolaychuks.cartservice.exceptions.CartNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = CartNotFoundException.class)
    protected ResponseEntity<Object> handleArticleNotFoundException(CartNotFoundException exception){
        log.info("Could not find Cart, id: {}", exception.getCartId());

        return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
    }
}
