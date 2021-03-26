package com.nikolaychuks.orderservice.repository;

import com.nikolaychuks.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
