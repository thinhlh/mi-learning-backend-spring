package com.thinhlh.mi_learning_backend.app.course.domain.entity;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseSectionResponse;
import com.thinhlh.mi_learning_backend.app.teacher.controller.dto.TeacherResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseV2 {
    // Course properties
    private UUID id;
    private String title;
    private String description;
    private Integer length;
    private String background;
    private String icon;
    private Double price;
    private String category;


    // Student - course properties
    private Boolean enrolled;
    private boolean saved;

    // Teacher - course properties
    private TeacherResponse teacher;

    // Rating
    private CourseRatingResponse courseRatings;

    // Student - course in depth properties
    private UUID currentLesson;
    private List<StudentCourseSectionResponse> sections;
}
