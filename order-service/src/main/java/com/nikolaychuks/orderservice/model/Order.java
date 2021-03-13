package com.nikolaychuks.orderservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long price;
}
