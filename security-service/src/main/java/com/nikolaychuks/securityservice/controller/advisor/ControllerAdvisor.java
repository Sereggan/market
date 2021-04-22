package com.nikolaychuks.securityservice.controller.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.message.AuthException;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = AuthException.class)
    protected ResponseEntity<Object> handle403(AuthException exception) {

        return new ResponseEntity<>("401 error", HttpStatus.UNAUTHORIZED);
    }


}
