package com.nikolaychuks.orderservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderNotFoundException extends RuntimeException {
    private String id;
}
