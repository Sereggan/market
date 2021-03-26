package com.nikolaychuks.paymentservice.service.events;

import com.nikolaychuks.paymentservice.message.Message;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Slf4j
@Service
public class EventService {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }
}
