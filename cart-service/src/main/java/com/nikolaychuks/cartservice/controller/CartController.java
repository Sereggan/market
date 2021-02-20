package com.nikolaychuks.cartservice.controller;

import com.nikolaychuks.cartservice.model.Cart;
import com.nikolaychuks.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId){
        return ResponseEntity.ok(cartService.findCartById(cartId));
    }
}
