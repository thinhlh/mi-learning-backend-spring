package com.thinhlh.mi_learning_backend.app.student_course.domain.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class GetStudentCourseDetailParams {
    private String email;
    private UUID courseId;
}
