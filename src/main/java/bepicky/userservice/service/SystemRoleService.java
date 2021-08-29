package bepicky.userservice.service;

import bepicky.userservice.entity.SystemRole;
import bepicky.userservice.repository.SystemRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemRoleService implements IRoleService {

    @Autowired
    private SystemRoleRepository repository;

    @Override
    public SystemRole findByName(SystemRole.Name name) {
        return repository.findByName(name).orElseThrow(() -> new IllegalArgumentException(name + ":role:404"));
    }
}
