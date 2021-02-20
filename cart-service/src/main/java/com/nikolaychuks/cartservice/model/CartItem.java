package com.nikolaychuks.cartservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="CartItem")
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Long price = 0L;
    private Long quantity = 0L;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
