package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyCourseResponse {

    private UUID id;
    private String title;
    private String background;
    private String icon;
    private int lessonsFinished;
    private int totalLesson;
    private String currentLessonTitle;
}
