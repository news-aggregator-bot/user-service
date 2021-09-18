package bepicky.userservice.nats;

import bepicky.userservice.service.UserNotificationService;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageAdminListener {
    @Autowired
    private Connection natsConnection;

    @Autowired
    private UserNotificationService notificationService;

    @Value("${topics.message.admin}")
    private String message;

    @PostConstruct
    public void createDispatcher() {
        Dispatcher dispatcher = natsConnection.createDispatcher(msg -> notificationService.notifyAdmin(new String(msg.getData())));
        dispatcher.subscribe(message);
    }
}
