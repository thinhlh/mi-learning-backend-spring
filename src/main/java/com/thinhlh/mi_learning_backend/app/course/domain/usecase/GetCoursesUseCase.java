package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseMapper;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCoursesUseCase implements BaseUseCase<String, List<CourseResponse>> {

    private final CourseService courseService;


    @Override
    public List<CourseResponse> invoke(String email) throws RuntimeException {
        return courseService.getAllCourses(email);
    }
}
