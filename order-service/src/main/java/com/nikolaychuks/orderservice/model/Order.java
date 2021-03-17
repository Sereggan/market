package com.nikolaychuks.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order")
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long price;

    private OrderStatus orderStatus;

    public static enum OrderStatus {
        ORDER_CREATED,
        ORDER_REJECTED,
        ORDER_CONFIRMED,
        ORDER_COMPLETED
    }
}
