package com.nikolaychuks.paymentservice.service.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolaychuks.paymentservice.message.Message;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Slf4j
@Service
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
