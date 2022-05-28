package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.teacher.controller.dto.TeacherMapper;
import com.thinhlh.mi_learning_backend.app.teacher.controller.dto.TeacherResponse;
import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class}, uses = {TeacherMapper.class})
public interface CourseMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Course toCourse(CourseRequest request);

    @Mapping(target = "courseRatings", source = "ratings")
    @Mapping(target = "category", source = "course.category.title")
    CourseResponse toResponse(Course course, CourseRatingResponse ratings, boolean enrolled);


    MyCourseResponse toMyCourseResponse(Course course, Long totalLesson, Long lessonsFinished, String currentLessonTitle);

    @Mapping(target = "teacherName", source = "teacherName")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "category", source = "course.category.title")
    RecommendationCourseResponse toRecommendation(Course course, String teacherName, double rating);

}
