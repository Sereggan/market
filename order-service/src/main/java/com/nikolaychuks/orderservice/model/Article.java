package com.nikolaychuks.orderservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="article")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private Long price = 0L;
    private Long quantity = 0L;
}
