package com.nikolaychuks.paymentservice.service.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolaychuks.paymentservice.events.OrderConfirmedEvent;
import com.nikolaychuks.paymentservice.exceptions.CouldNotCompletOrderException;
import com.nikolaychuks.paymentservice.message.Message;
import com.nikolaychuks.paymentservice.service.internal.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventListenerService {

    private final PaymentService paymentService;
    private final EventService eventService;
    private final ObjectMapper objectMapper;

    private final String ORDER_CONFIRMED_TOPIC = "order_confirmed";
    private final String ORDER_COMPLETED_TOPIC = "order_completed";

    @SneakyThrows
    @KafkaListener(topics = ORDER_CONFIRMED_TOPIC)
    private void consumeOrderConfirmed(Message<String> messageJson) {
        OrderConfirmedEvent event = objectMapper.readValue(messageJson.getData(), OrderConfirmedEvent.class);

        log.info("Receiver OrderCreated Event with order id: {}", event.getOrderId());

        try {
            paymentService.payOrder(event.getOrderId(), event.getPrice());

            log.info("Sending OrderConfirmed Event with order id: {}", event.getOrderId());

            messageJson.setData(String.valueOf(event.getOrderId()));

            eventService.sendMessage(ORDER_COMPLETED_TOPIC, messageJson);
        } catch (CouldNotCompletOrderException e) {
            log.info("Couldn't confirm order with order id: {}", event.getOrderId());
        }
    }
}
