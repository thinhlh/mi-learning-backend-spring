package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.CourseResponseV2;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCourseDetailUseCase implements BaseUseCase<GetCourseDetailParams, CourseResponseV2> {

    private final CourseService courseService;

    @Override
    public CourseResponseV2 invoke(GetCourseDetailParams data) throws RuntimeException {
        return courseService.getCourseDetail(data);
    }
}
