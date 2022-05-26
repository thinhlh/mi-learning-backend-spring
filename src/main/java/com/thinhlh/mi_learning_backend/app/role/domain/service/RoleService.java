package com.thinhlh.mi_learning_backend.app.role.domain.service;

import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    Role createRole(Role role);
}
