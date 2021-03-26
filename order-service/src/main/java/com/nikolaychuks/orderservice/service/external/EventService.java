package com.nikolaychuks.orderservice.service.external;

import com.nikolaychuks.orderservice.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(String topic, Message message) {
        log.info("Sending message to topic: {} , data: {}", topic, message);

        kafkaTemplate.send(topic, message);
    }

}
