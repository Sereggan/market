package com.nikolaychuks.paymentservice.service.events;

import com.nikolaychuks.paymentservice.events.OrderConfirmedEvent;
import com.nikolaychuks.paymentservice.exceptions.CouldNotCompletOrderException;
import com.nikolaychuks.paymentservice.message.Message;
import com.nikolaychuks.paymentservice.service.internal.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventListenerService {

    private final PaymentService paymentService;
    private final EventService eventService;
    private final String ORDER_CONFIRMED_TOPIC = "order_confirmed";
    private final String ORDER_COMPLETED_TOPIC = "order_completed";

    @KafkaListener(topics = ORDER_CONFIRMED_TOPIC)
    private void consumeOrderConfirmed(Message messageJson) {
        OrderConfirmedEvent event = (OrderConfirmedEvent) messageJson.getData();
        log.info("Receiver OrderCreated Event with order id: {}", event.getOrderId());
        try {
            paymentService.payOrder(event.getOrderId(), event.getPrice());

            log.info("Sending OrderConfirmed Event with order id: {}", event.getOrderId());

            messageJson.setData(event.getOrderId());

            eventService.sendMessage(ORDER_COMPLETED_TOPIC, messageJson);
        } catch (CouldNotCompletOrderException e) {
            log.info("Couldn't confirm order with order id: {}", event.getOrderId());
        }
    }
}
