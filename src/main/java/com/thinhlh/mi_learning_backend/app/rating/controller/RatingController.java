package com.thinhlh.mi_learning_backend.app.rating.controller;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.rating.domain.usecase.CreateRatingUseCase;
import com.thinhlh.mi_learning_backend.app.rating.domain.usecase.GetCourseRatingsUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RatingController extends BaseController {

    private final GetCourseRatingsUseCase getCourseRatingsUseCase;
    private final CreateRatingUseCase createRatingUseCase;

    @GetMapping("/ratings")
    private ResponseEntity<BaseResponse<CourseRatingResponse>> getRatings(@RequestParam @NotNull UUID courseId) {
        return successResponse(getCourseRatingsUseCase.invoke(courseId));
    }

}
