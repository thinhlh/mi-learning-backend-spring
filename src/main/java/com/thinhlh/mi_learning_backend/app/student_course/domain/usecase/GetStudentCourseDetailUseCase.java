package com.thinhlh.mi_learning_backend.app.student_course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseResponse;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetStudentCourseDetailUseCase implements BaseUseCase<GetStudentCourseDetailParams, StudentCourseResponse> {

    private final StudentCourseService studentCourseService;

    @Override
    public StudentCourseResponse invoke(GetStudentCourseDetailParams data) throws RuntimeException {
        return studentCourseService.getStudentCourseDetail(data);
    }
}
