package com.thinhlh.mi_learning_backend.app.teacher.controller.dto;

import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "name", source = "teacher.user.name")
    @Mapping(target = "avatar", source = "teacher.user.avatar")
    TeacherResponse toTeacherResponse(Teacher teacher);

}
