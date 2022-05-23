package com.thinhlh.mi_learning_backend.app.role.controller.dto;

import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toResponse(Role role);
}
