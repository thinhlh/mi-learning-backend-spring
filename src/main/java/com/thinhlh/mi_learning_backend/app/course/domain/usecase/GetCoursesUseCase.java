package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.CourseResponseV2;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCoursesUseCase implements BaseUseCase<GetCourseParams, List<CourseResponseV2>> {

    private final CourseService courseService;

    @Override
    public List<CourseResponseV2> invoke(GetCourseParams getCourseParams) throws RuntimeException {
        return courseService.getCourses(getCourseParams);
    }
}
