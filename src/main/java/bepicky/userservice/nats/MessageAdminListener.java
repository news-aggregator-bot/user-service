package bepicky.userservice.nats;

import bepicky.common.domain.admin.TextMessage;
import bepicky.userservice.service.UserNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Slf4j
public class MessageAdminListener {
    @Autowired
    private Connection natsConnection;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserNotificationService notificationService;

    @Value("${topics.message.admin}")
    private String message;

    @PostConstruct
    public void createDispatcher() {
        Dispatcher dispatcher = natsConnection.createDispatcher(msg -> {
            try {
                String t = om.readValue(msg.getData(), String.class);
                handle(t);
            } catch (IOException e) {
                log.error("newsnote:notificaton:failed " + e.getMessage());
            }
        });
        dispatcher.subscribe(message);
    }

    public void handle(String text) {
        notificationService.notifyAdmin(text);
    }
}
