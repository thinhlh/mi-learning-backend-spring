package com.thinhlh.mi_learning_backend.app.rating.controller.dto;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(source = "ratings", target = "ratings")
    @Mapping(target = "average", source = "average")
    @Mapping(target = "ratingAverageByStar", source = "ratingAverageByStar")
    @Mapping(target = "totalRating", source = "totalRating")
    CourseRatingResponse toCourseRatingResponse(List<Rating> ratings, List<Integer> ratingAverageByStar, Double average, Integer totalRating);

}
