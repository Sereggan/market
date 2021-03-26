package com.nikolaychuks.paymentservice.repository;

import com.nikolaychuks.paymentservice.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentInfo, Long> {
}
