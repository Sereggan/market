package com.nikolaychuks.paymentservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentInfoNotFoundException extends RuntimeException {
    private Long id;
}
