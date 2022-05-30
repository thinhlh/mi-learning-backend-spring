package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetSavedCoursesUseCase implements BaseUseCase<String, List<CourseResponse>> {

    private final CourseService courseService;


    @Override
    public List<CourseResponse> invoke(String email) throws RuntimeException {
        return courseService.getSavedCourses(email);
    }
}