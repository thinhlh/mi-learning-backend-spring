package com.thinhlh.mi_learning_backend.app.user.controller.dto;

import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "role",source = "role.title")
    UserDetailResponse toUserDetailResponse(User user);
}
