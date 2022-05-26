package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface CourseMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Course toCourse(CourseRequest request);

    @Mapping(target = "courseRatings", source = "ratings")
    @Mapping(target = "category", source = "course.category.title")
    CourseResponse toResponse(Course course, CourseRatingResponse ratings);

}
