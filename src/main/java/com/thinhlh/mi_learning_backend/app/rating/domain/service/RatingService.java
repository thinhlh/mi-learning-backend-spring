package com.thinhlh.mi_learning_backend.app.rating.domain.service;


import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CreateRatingRequest;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingRequest;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingResponse;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;

import java.util.UUID;

public interface RatingService {
    CourseRatingResponse getCourseRating(UUID courseId);

    Rating createRating(RatingRequest request);

    RatingResponse createRating(CreateRatingRequest request);
}
