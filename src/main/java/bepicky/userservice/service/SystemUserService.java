package bepicky.userservice.service;

import bepicky.userservice.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements IUserService {

    @Autowired
    private SystemUserRepository repository;
}
