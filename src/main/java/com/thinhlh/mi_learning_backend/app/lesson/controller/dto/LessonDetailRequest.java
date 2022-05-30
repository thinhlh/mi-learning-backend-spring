package com.thinhlh.mi_learning_backend.app.lesson.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDetailRequest {
    private String email;
    private UUID lessonId;
}
