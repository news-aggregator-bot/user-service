package bepicky.userservice.service;

import bepicky.userservice.entity.SystemRole;

import java.util.Set;

public interface IRoleService {

    SystemRole findByName(SystemRole.Name name);
}
