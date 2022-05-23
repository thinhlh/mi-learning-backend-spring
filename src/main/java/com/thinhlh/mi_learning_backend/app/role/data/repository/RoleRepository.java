package com.thinhlh.mi_learning_backend.app.role.data.repository;

import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role getRoleByTitle(String title);

}
