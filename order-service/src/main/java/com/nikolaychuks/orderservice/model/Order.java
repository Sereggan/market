package com.nikolaychuks.orderservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Article> articleList;

    private String userId;

    private Long price;
}
