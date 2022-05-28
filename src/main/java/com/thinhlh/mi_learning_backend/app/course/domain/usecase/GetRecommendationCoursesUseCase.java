package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.RecommendationCourseResponse;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetRecommendationCoursesUseCase implements BaseUseCase<String, List<RecommendationCourseResponse>> {
    private final CourseService courseService;

    @Override
    public List<RecommendationCourseResponse> invoke(String data) throws RuntimeException {
        return courseService.getRecommendationCourses(data);
    }
}
