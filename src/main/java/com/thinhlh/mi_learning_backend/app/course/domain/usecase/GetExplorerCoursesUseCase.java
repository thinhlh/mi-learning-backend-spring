package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor

/*
 * Get courses that user not enrolled
 * */
public class GetExplorerCoursesUseCase implements BaseUseCase<String, List<CourseResponse>> {

    private final CourseService courseService;

    @Override
    public List<CourseResponse> invoke(String email) throws RuntimeException {
        return courseService.getExplorerCourses(email);
    }
}
