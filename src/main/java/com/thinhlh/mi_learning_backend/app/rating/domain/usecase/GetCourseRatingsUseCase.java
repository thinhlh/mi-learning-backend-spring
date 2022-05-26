package com.thinhlh.mi_learning_backend.app.rating.domain.usecase;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetCourseRatingsUseCase implements BaseUseCase {

    private final RatingService service;

    @Override
    public CourseRatingResponse invoke(Object data) throws RuntimeException {
        return service.getCourseRating((UUID) data);
    }
}
