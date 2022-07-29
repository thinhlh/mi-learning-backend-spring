package com.thinhlh.mi_learning_backend.app.rating.data.service;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.*;
import com.thinhlh.mi_learning_backend.app.rating.data.repository.RatingRepository;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.app.student_course.data.repository.StudentCourseRepository;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;
    private final StudentCourseRepository studentCourseRepository;
    private final RatingMapper mapper;

    @Override
    public CourseRatingResponse getCourseRating(UUID courseId) {
        var ratings = repository.findByStudentCourse_Id_CourseId(courseId);

        var totalRatings = ratings.size();

        List<Integer> averageForEachStar = new ArrayList<>(5);

        for (int i = 1; i <= 5; i++) {
            averageForEachStar.add(averagePercentageForEachStar(ratings, totalRatings, i));
        }


        return mapper.toCourseRatingResponse(
                ratings,
                averageForEachStar,
                Math.round(ratings
                        .stream()
                        .flatMapToDouble(rating ->
                                java.util.stream.DoubleStream.of(rating.getValue())).average()
                        .orElse(0) * 100.0) / 100.0,
                totalRatings);
    }

    @Override
    @Transactional
    public Rating createRating(RatingRequest request) {
        var rating = mapper.toRating(request);

        var studentCourse = studentCourseRepository.findByStudent_IdAndCourse_Id(request.getStudentId(), request.getCourseId());

        if (studentCourse == null) {
//            throw new NotFoundException();
        } else {
            rating.setStudentCourse(studentCourse);
        }

        return repository.save(rating);
    }

    @Override
    @Transactional
    public RatingResponse createRating(CreateRatingRequest request) {
        var rating = mapper.toRating(request);

        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(request.getEmail(), request.getCourseId());

        if (studentCourse == null) {
//            throw new NotFoundException();
        } else {
            rating.setStudentCourse(studentCourse);
        }

        rating = repository.save(rating);

        return mapper.toRatingResponse(rating);
    }

    private Integer averagePercentageForEachStar(List<Rating> ratings, Integer totalRatings, Integer value) {
        return ratings
                .stream()
                .filter(rating -> rating.getValue() != null && rating.getValue().equals(value))
                .toList()
                .size()
                * 100 / (totalRatings.doubleValue() == 0 ? 1 : totalRatings);
    }
}
