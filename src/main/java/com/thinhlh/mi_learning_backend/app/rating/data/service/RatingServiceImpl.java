package com.thinhlh.mi_learning_backend.app.rating.data.service;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingMapper;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingRequest;
import com.thinhlh.mi_learning_backend.app.rating.data.repository.RatingRepository;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;
    private final RatingMapper mapper;

    @Override
    public CourseRatingResponse getCourseRating(UUID courseId) {
        var ratings = repository.findByStudentCourse_Id_CourseId(courseId);

        var totalRatings = ratings.size();

        List<Integer> averageForEachStar = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            averageForEachStar.add(0, averagePercentageForEachStar(ratings, totalRatings, i + 1));
        }


        return mapper.toCourseRatingResponse(
                ratings,
                averageForEachStar,
                ratings
                        .stream()
                        .flatMapToDouble(rating ->
                                java.util.stream.DoubleStream.of(rating.getValue())).average()
                        .orElse(0),
                totalRatings);
    }

    @Override
    public Rating createRating(RatingRequest request) {

        return null;
    }

    private Integer averagePercentageForEachStar(List<Rating> ratings, Integer totalRatings, Integer value) {
        return ratings
                .stream()
                .filter(rating -> rating.getValue().equals(value))
                .toList()
                .size()
                / (totalRatings == 0 ? 1 : totalRatings) * 100;
    }
}
