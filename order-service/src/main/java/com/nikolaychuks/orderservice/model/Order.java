package com.nikolaychuks.orderservice.model;

import com.nikolaychuks.orderservice.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long price;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
