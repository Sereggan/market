package com.nikolaychuks.cartservice.service;

import com.nikolaychuks.cartservice.exceptions.CartNotFoundException;
import com.nikolaychuks.cartservice.model.Cart;
import com.nikolaychuks.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;


    public Cart findCartById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException(cartId));
    }
}
