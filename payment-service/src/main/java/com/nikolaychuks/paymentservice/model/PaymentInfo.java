package com.nikolaychuks.paymentservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "payment_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userID")
    private Long userId;
    private Long balance;
}
