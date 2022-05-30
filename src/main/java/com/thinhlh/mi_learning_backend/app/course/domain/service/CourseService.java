package com.thinhlh.mi_learning_backend.app.course.domain.service;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.MyCourseResponse;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.RecommendationCourseResponse;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<CourseResponse> getAllCourses(String email);

    List<CourseResponse> getSavedCourses(String email);

    List<CourseResponse> getExplorerCourses(String email);

    Course createCourse(Course course);

    List<MyCourseResponse> getMyCourses(String email);

    List<RecommendationCourseResponse> getRecommendationCourses(String email);

    CourseResponse getCourseDetail(UUID courseId, String email);

    List<Lesson> getLessonInCourse(UUID courseId);

    // Return lesson Id
    Lesson getLastFinishedLessonInCourse(UUID courseId, String email);
}
