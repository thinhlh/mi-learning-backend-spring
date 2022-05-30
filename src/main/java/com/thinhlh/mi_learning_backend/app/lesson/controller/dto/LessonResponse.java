package com.thinhlh.mi_learning_backend.app.lesson.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LessonResponse {
    private UUID id;
    private String title;
    private int order;
    private VideoLessonResponse video;
    private TestLessonResponse test;
}
