package com.thinhlh.mi_learning_backend.app.student_course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.ToggleSaveCourseRequest;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ToggleSaveCourseUseCase implements BaseUseCase<ToggleSaveCourseRequest, Boolean> {
    private final StudentCourseService studentCourseService;

    @Override
    public Boolean invoke(ToggleSaveCourseRequest data) throws RuntimeException {
        return studentCourseService.toggleSavedCourse(data);
    }
}
