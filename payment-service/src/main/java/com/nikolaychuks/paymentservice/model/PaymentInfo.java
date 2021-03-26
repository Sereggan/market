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
    private Long userId;
    private Long balance;
}
