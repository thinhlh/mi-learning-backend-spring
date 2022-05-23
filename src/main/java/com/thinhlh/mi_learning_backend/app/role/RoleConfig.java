package com.thinhlh.mi_learning_backend.app.role;

import com.thinhlh.mi_learning_backend.app.role.data.repository.RoleRepository;
import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleConfig implements CommandLineRunner {

    private final RoleRepository repository;

    @Override
    public void run(String... args) {
//        var roleNames = Arrays.stream(Role.RoleName.values()).toList();
//
//        var roles = new ArrayList<Role>();
//        for (Role.RoleName role : roleNames) {
//            roles.add(new Role(UUID.randomUUID(), role.name()));
//        }
//
//        repository.saveAll(roles);
    }
}
