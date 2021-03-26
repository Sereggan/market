package com.nikolaychuks.paymentservice.service.internal;

import com.nikolaychuks.paymentservice.exceptions.CouldNotCompletOrderException;
import com.nikolaychuks.paymentservice.exceptions.PaymentInfoNotFoundException;
import com.nikolaychuks.paymentservice.model.PaymentInfo;
import com.nikolaychuks.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentInfo findPaymentInfoById(String id) {
        return paymentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new PaymentInfoNotFoundException(Long.parseLong(id)));
    }

    public PaymentInfo createPaymentInfo(PaymentInfo paymentInfo) {
        log.info("Creating PaymentInfo for user: {}, balance: {}", paymentInfo.getUserId(), paymentInfo.getBalance());
        return paymentRepository.save(paymentInfo);
    }

    @Transactional(rollbackFor = CouldNotCompletOrderException.class)
    public void payOrder(Long id, Long price) {
        PaymentInfo paymentInfo = paymentRepository.findById((id)).orElseThrow(() -> new PaymentInfoNotFoundException(id));

        if (paymentInfo.getBalance() - price >= 0) {
            paymentInfo.setBalance(paymentInfo.getBalance() - price);
        } else {
            throw new CouldNotCompletOrderException();
        }
    }
}
