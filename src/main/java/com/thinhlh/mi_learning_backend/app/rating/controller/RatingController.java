package com.thinhlh.mi_learning_backend.app.rating.controller;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CreateRatingRequest;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingRequest;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingResponse;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import com.thinhlh.mi_learning_backend.app.rating.domain.usecase.CreateRatingUseCase;
import com.thinhlh.mi_learning_backend.app.rating.domain.usecase.GetCourseRatingsUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/rating")
    private ResponseEntity<BaseResponse<RatingResponse>> createRating(@RequestBody CreateRatingRequest ratingRequest, HttpServletRequest request) {
        ratingRequest.setEmail(ServletHelper.retrieveUsernameAndRolesFromRequest(request, null).getFirst());

        return successResponse(createRatingUseCase.invoke(ratingRequest));
    }

}
