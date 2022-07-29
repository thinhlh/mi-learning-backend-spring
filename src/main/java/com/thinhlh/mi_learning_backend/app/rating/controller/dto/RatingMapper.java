package com.thinhlh.mi_learning_backend.app.rating.controller.dto;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface RatingMapper {

    @Mapping(source = "ratings", target = "ratings")
    @Mapping(target = "average", source = "average")
    @Mapping(target = "ratingAverageByStar", source = "ratingAverageByStar")
    @Mapping(target = "totalRating", source = "totalRating")
    CourseRatingResponse toCourseRatingResponse(List<Rating> ratings, List<Integer> ratingAverageByStar, Double average, Integer totalRating);

    @Mapping(target = "name", source = "rating.studentCourse.student.user.name")
    @Mapping(target = "avatar", source = "rating.studentCourse.student.user.avatar")
    RatingResponse toRatingResponse(Rating rating);


    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "value", source = "rating")
    Rating toRating(RatingRequest request);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "value", source = "rating")
    Rating toRating(CreateRatingRequest request);


}
