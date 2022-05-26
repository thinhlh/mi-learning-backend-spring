package com.thinhlh.mi_learning_backend.app.rating.controller.dto;

import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import java.util.List;

@Data
public class CourseRatingResponse {

    private Double average;
    private List<Integer> ratingAverageByStar;
    private List<Rating> ratings;
    private Integer totalRating;

}
