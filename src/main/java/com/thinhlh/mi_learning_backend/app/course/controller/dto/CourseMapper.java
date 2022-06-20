package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.CourseResponseV2;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.teacher.controller.dto.TeacherMapper;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class}, uses = {TeacherMapper.class})
public interface CourseMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Course toCourse(CourseRequest request);

    @Mapping(target = "courseRatings", source = "ratings")
    @Mapping(target = "category", source = "course.category.title")
    CourseResponse toResponse(Course course, CourseRatingResponse ratings, boolean enrolled, UUID currentLesson, boolean saved);


    MyCourseResponse toMyCourseResponse(Course course, Long lessonsFinished, Long totalLesson, String currentLessonTitle);

    @Mapping(target = "teacherName", source = "teacherName")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "category", source = "course.category.title")
    RecommendationCourseResponse toRecommendation(Course course, String teacherName, double rating);

    @Mapping(target = "enrolled", ignore = true)
    @Mapping(target = "saved", ignore = true)
    @Mapping(target = "courseRatings", ignore = true)
    @Mapping(target = "currentLesson", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "category", source = "course.category.title")
    CourseResponseV2 toCourseResponseV2(Course course);

}
