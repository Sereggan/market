package com.nikolaychuks.cartservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;

}
