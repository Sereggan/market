package com.nikolaychuks.orderservice.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolaychuks.orderservice.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void sendMessage(String topic, Message message) {
        log.info("Sending message to topic: {} , data: {}", topic, message);

        String data = objectMapper.writeValueAsString(message.getData());
        message.setData(data);
        kafkaTemplate.send(topic, message);
    }

}
