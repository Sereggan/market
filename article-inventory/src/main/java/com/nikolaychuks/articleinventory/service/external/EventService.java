package com.nikolaychuks.articleinventory.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolaychuks.articleinventory.message.Message;
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
    private final ObjectMapper mapper;

    @SneakyThrows
    public void sendMessage(String topic, Message message) {
        log.info("Sending message to topic: {} , data: {}", topic, message);

        String data = mapper.writeValueAsString(message.getData());
        message.setData(data);
        kafkaTemplate.send(topic, message);
    }

}
