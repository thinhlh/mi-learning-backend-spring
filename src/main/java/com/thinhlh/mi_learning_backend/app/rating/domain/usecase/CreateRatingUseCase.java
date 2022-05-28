package com.thinhlh.mi_learning_backend.app.rating.domain.usecase;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingRequest;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRatingUseCase implements BaseUseCase<RatingRequest, Rating> {

    private final RatingService ratingService;

    @Override
    public Rating invoke(RatingRequest data) throws RuntimeException {
        return ratingService.createRating(data);
    }
}
