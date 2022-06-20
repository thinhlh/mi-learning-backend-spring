package com.thinhlh.mi_learning_backend.app.student_course.controller;

import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseLessonResponse;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseResponse;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.ToggleSaveCourseRequest;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.app.student_course.domain.usecase.GetStudentCourseDetailParams;
import com.thinhlh.mi_learning_backend.app.student_course.domain.usecase.GetStudentCourseDetailUseCase;
import com.thinhlh.mi_learning_backend.app.student_course.domain.usecase.ToggleSaveCourseUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StudentCourseController extends BaseController {
    private final ToggleSaveCourseUseCase toggleSaveCourseUseCase;
    private final StudentCourseService studentCourseService;

    @PostMapping("/course/save")
    private ResponseEntity<BaseResponse<Boolean>> toggleSaveCourse(HttpServletRequest request, @RequestBody @Valid ToggleSaveCourseRequest toggleSaveCourseRequest) {
        return successResponse(
                toggleSaveCourseUseCase.invoke(
                        ToggleSaveCourseRequest
                                .builder()
                                .email(ServletHelper.retrieveUsernameAndRolesFromRequest(request, null).getFirst())
                                .courseId(toggleSaveCourseRequest.getCourseId())
                                .saved(toggleSaveCourseRequest.isSaved())
                                .build()
                )
        );
    }

    @PostMapping("/purchase")
    private ResponseEntity<BaseResponse<Boolean>> purchaseCourse(HttpServletRequest request, @RequestParam @Valid UUID courseId) {
        return successResponse(
                studentCourseService.studentJoinCourse(
                        ServletHelper.retrieveUsernameAndRolesFromRequest(
                                request, null).getFirst()
                        , courseId
                )
        );
    }
}
