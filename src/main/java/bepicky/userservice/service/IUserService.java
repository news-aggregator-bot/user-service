package bepicky.userservice.service;

import bepicky.common.msg.admin.UserRegistration;
import bepicky.userservice.entity.SystemUser;

import java.util.Set;

public interface IUserService {

    void create(UserRegistration u);

    Set<SystemUser> findEnabledAdmins();
}
