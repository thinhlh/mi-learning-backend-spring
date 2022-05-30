package com.thinhlh.mi_learning_backend.app.student_course.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCourseResponse {
    private UUID courseId;
    private Integer length;
    private UUID currentLesson;

    private List<StudentCourseSectionResponse> sections;
}
