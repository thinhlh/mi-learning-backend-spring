package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetCourseDetailUseCase implements BaseUseCase<GetCourseDetailParams, CourseResponse> {

    private final CourseService courseService;

    @Override
    public CourseResponse invoke(GetCourseDetailParams data) throws RuntimeException {
        return courseService.getCourseDetail(data.getCourseId(), data.getEmail());
    }
}
