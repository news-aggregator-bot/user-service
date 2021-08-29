package bepicky.userservice.nats;

import bepicky.common.msg.admin.UserRegistration;
import bepicky.userservice.service.IUserService;
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
public class UserRegistrationMessageListener {

    @Autowired
    private Connection natsConnection;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private IUserService userService;

    @Value("${topics.user.register}")
    private String userRegistrationSubject;

    @PostConstruct
    public void createDispatcher() {
        Dispatcher dispatcher = natsConnection.createDispatcher(msg -> {
            try {
                UserRegistration t = om.readValue(msg.getData(), UserRegistration.class);
                handle(t);
            } catch (IOException e) {
                log.error("user:registration:failed " + e.getMessage());
            }
        });
        dispatcher.subscribe(userRegistrationSubject);
    }

    public void handle(UserRegistration u) {
        log.info("user:registration:{}", u);
        userService.create(u);
    }
}
