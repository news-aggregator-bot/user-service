package bepicky.userservice.service;

import bepicky.userservice.entity.SystemRole;
import bepicky.userservice.nats.TextMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemUserNotificationService implements UserNotificationService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private TextMessagePublisher textMessagePublisher;

    @Override
    @Transactional
    public void notifyAdmin(String text) {
        SystemRole admin = roleService.findByName(SystemRole.Name.ADMIN);
        admin.getUsers()
            .forEach(u -> textMessagePublisher.publish(u.getChatId(), text));
    }
}
