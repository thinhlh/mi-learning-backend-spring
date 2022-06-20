package com.thinhlh.mi_learning_backend.app.student_course.controller.dto;

import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.TestLesson;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.VideoLesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCourseLessonResponse {
    private UUID id;
    private String title;
    private Integer lessonOrder;
    private VideoLesson videoLesson;
    private TestLesson testLesson;
    private StudentCourseMetaDataResponse metadata;
}
