package com.nikolaychuks.cartservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartNotFoundException extends RuntimeException {

    private Long cartId;
}
