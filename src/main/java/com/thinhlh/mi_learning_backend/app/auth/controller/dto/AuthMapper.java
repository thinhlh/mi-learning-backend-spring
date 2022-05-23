package com.thinhlh.mi_learning_backend.app.auth.controller.dto;

import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface AuthMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "enabled", expression = "java(true)")
    @Mapping(target = "role", ignore = true)
    User toUser(RegisterRequest registerRequest);

}
