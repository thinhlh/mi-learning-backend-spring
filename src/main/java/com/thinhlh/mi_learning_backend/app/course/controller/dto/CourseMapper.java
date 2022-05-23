package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface CourseMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Course toCourse(CourseRequest request);

}
