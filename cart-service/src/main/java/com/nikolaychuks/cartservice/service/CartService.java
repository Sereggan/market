package com.nikolaychuks.cartservice.service;

import com.nikolaychuks.cartservice.exceptions.CartNotFoundException;
import com.nikolaychuks.cartservice.model.Cart;
import com.nikolaychuks.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;


    public Cart findCartById(Long cartId) {
        log.info("Finding cart by id: {}", cartId);

        return cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException(cartId));
    }
}
