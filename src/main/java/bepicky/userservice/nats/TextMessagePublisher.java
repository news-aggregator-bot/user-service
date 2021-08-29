package bepicky.userservice.nats;

import bepicky.common.domain.admin.TextMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class TextMessagePublisher {

    @Autowired
    private Connection connection;

    @Autowired
    private ObjectMapper om;

    @Value("${topics.message.text}")
    private String msgSubject;

    public void publish(Long chatId, String text) {

        TextMessage msg = new TextMessage(chatId, text);
        try {
            connection.publish(msgSubject, om.writeValueAsString(msg).getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {
            log.error("notify:{}:failed:{}", msgSubject, msg, e);
        }
    }
}
