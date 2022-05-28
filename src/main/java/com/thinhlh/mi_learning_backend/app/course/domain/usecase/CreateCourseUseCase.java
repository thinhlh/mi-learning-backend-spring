package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCourseUseCase implements BaseUseCase<Course, Course> {

    private final CourseService courseService;


    @Override
    public Course invoke(Course data) throws RuntimeException {
        return courseService.createCourse(data);
    }
}
