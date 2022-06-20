package com.thinhlh.mi_learning_backend.app.student_course.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseSectionResponse {
    private UUID id;
    private String title;
    private int finishedLesson;
    private int totalLesson;
    private long length;
    private List<StudentCourseLessonResponse> lessons = new ArrayList<>();
}
