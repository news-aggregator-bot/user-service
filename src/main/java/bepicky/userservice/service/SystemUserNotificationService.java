package bepicky.userservice.service;

import bepicky.userservice.nats.TextMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemUserNotificationService implements UserNotificationService {

    @Autowired
    private IUserService userService;

    @Autowired
    private TextMessagePublisher textMessagePublisher;

    @Override
    @Transactional
    public void notifyAdmin(String text) {
        userService.findEnabledAdmins()
            .forEach(u -> textMessagePublisher.publish(u.getChatId(), text));
    }
}
