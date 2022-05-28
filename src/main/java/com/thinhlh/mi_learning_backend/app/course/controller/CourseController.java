package com.thinhlh.mi_learning_backend.app.course.controller;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.*;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.*;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CourseController extends BaseController {

    private final CourseMapper mapper;
    private final GetCoursesUseCase getCoursesUseCase;
    private final GetCourseDetailUseCase getCourseDetailUseCase;
    private final CreateCourseUseCase createCourseUseCase;
    private final GetMyCoursesUseCase getMyCoursesUseCase;
    private final GetRecommendationCoursesUseCase getRecommendationCoursesUseCase;


    @GetMapping("/courses")
    ResponseEntity<BaseResponse<List<CourseResponse>>> getAllCourses(HttpServletRequest request) {
        return successResponse(getCoursesUseCase.invoke(ServletHelper
                .retrieveUsernameAndRolesFromRequest(request, null)
                .getFirst()));
    }

    @PostMapping("/course")
    ResponseEntity<BaseResponse<Course>> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        return successResponse(createCourseUseCase.invoke(mapper.toCourse(courseRequest)));
    }

    @GetMapping("/course")
    ResponseEntity<BaseResponse<CourseResponse>> getCourseResponse(HttpServletRequest request, @RequestParam @NotBlank UUID courseId) {
        return successResponse(getCourseDetailUseCase.invoke(new GetCourseDetailParams(
                courseId,
                ServletHelper
                        .retrieveUsernameAndRolesFromRequest(request, null)
                        .getFirst()
        )));
    }

    @GetMapping("/courses/me")
    private ResponseEntity<BaseResponse<List<MyCourseResponse>>> getMyCourses(HttpServletRequest request) {

        return successResponse(
                getMyCoursesUseCase.invoke(
                        ServletHelper
                                .retrieveUsernameAndRolesFromRequest(request, null)
                                .getFirst()
                )
        );
    }

    @GetMapping("/courses/recommend")
    private ResponseEntity<BaseResponse<List<RecommendationCourseResponse>>> getRecommendationCourses(HttpServletRequest request) {
        return successResponse(getRecommendationCoursesUseCase.invoke(ServletHelper
                .retrieveUsernameAndRolesFromRequest(request, null)
                .getFirst()
        ));
    }

}
