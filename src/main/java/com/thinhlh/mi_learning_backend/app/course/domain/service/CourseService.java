package com.thinhlh.mi_learning_backend.app.course.domain.service;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.GetCourseDetailParams;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.GetCourseParams;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.CourseResponseV2;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<CourseResponseV2> getCourses(GetCourseParams params);

    Course createCourse(Course course);

    CourseResponseV2 getCourseDetail(GetCourseDetailParams params);

    List<Lesson> getLessonsInCourse(UUID courseId);

    // Return lesson Id
    Lesson getLastFinishedLessonInCourse(UUID courseId, String email);
}
