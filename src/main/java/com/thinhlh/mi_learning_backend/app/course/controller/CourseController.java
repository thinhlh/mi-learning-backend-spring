package com.thinhlh.mi_learning_backend.app.course.controller;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.*;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.GetCourseType;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.*;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.CourseResponseV2;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CourseController extends BaseController {

    private final CourseMapper mapper;
    private final GetCoursesUseCase getCoursesUseCase;
    private final CreateCourseUseCase createCourseUseCase;

    private final GetCourseDetailUseCase getCourseDetailUseCase;

    @PostMapping("/course")
    ResponseEntity<BaseResponse<Course>> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        return successResponse(createCourseUseCase.invoke(mapper.toCourse(courseRequest)));
    }

    @GetMapping("/courses")
    ResponseEntity<BaseResponse<List<CourseResponseV2>>> getCourses(
            @Valid @RequestParam(required = false) GetCourseType type,
            @Valid @RequestParam(required = false) UUID categoryId,
            HttpServletRequest request) {
        return successResponse(
                getCoursesUseCase.invoke(
                        new GetCourseParams(ServletHelper.retrieveUsernameAndRolesFromRequest(request, null)
                                .getFirst(),
                                type,
                                categoryId
                        )
                )
        );
    }

    @GetMapping("/course/detail")
    ResponseEntity<BaseResponse<CourseResponseV2>> getCourseDetail(@Valid @RequestParam @NotEmpty UUID courseId, HttpServletRequest request) {
        return successResponse(
                getCourseDetailUseCase.invoke(
                        new GetCourseDetailParams(
                                courseId,
                                ServletHelper.retrieveUsernameAndRolesFromRequest(request, null)
                                        .getFirst()
                        )
                )
        );
    }

}
