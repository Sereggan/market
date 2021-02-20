package com.nikolaychuks.cartservice.repository;

import com.nikolaychuks.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
