package com.nikolaychuks.articleinventory.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="article")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Long price = 0L;
    private Long quantity = 0L;
}
