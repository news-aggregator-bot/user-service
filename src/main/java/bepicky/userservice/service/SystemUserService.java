package bepicky.userservice.service;

import bepicky.common.msg.admin.UserRegistration;
import bepicky.userservice.entity.SystemRole;
import bepicky.userservice.entity.SystemUser;
import bepicky.userservice.repository.SystemUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class SystemUserService implements IUserService {

    @Autowired
    private SystemUserRepository repository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserNotificationService notificationService;

    @Override
    @Transactional
    public void create(UserRegistration u) {
        String[] roles = u.getRoles().split("\\s");
        if (roles.length == 0) {
            log.error("user:registration:failed:no roles provided:{}", u);
            notificationService.notifyAdmin("FAILED:user:registration:no roles provided:" + u.toString());
            return;
        }
        if (!repository.findByChatId(u.getChatId()).isEmpty()) {
            log.info("user:registration:exists:{}", u.getChatId());
            return;
        }
        Set<SystemRole> systemRoles = Arrays.stream(roles).map(SystemRole.Name::valueOf)
            .map(roleService::findByName)
            .collect(Collectors.toSet());

        SystemUser user = new SystemUser();
        user.setChatId(u.getChatId());
        user.setUsername(u.getUsername());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setStatus(SystemUser.Status.NEW);
        user.setRoles(systemRoles);

        SystemUser saved = repository.save(user);
        log.info("user:registration:success:{}", saved);
        notificationService.notifyAdmin("SUCCESS:user:registration:" + saved.toString());
    }

    @Override
    public Set<SystemUser> findEnabledAdmins() {
        SystemRole admin = roleService.findByName(SystemRole.Name.ADMIN);
        SystemRole gods = roleService.findByName(SystemRole.Name.GOD);
        return Stream.concat(admin.getUsers().stream(), gods.getUsers().stream())
            .filter(u -> u.getStatus() == SystemUser.Status.ENABLED)
            .collect(Collectors.toSet());
    }
}
