package com.thinhlh.mi_learning_backend.app.course.controller;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseMapper;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseRequest;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.CreateCourseUseCase;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.GetCoursesUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CourseController extends BaseController {

    private final CourseMapper mapper;
    private final GetCoursesUseCase getCoursesUseCase;
    private final CreateCourseUseCase createCourseUseCase;


    @GetMapping("/courses")
    ResponseEntity<BaseResponse<List<Course>>> getAllCourses() {
        return successResponse(getCoursesUseCase.invoke(null));
    }

    @PostMapping("/course")
    ResponseEntity<BaseResponse<Course>> createCourse(@Valid @RequestBody CourseRequest request) {
        return successResponse(createCourseUseCase.invoke(mapper.toCourse(request)));
    }

}
