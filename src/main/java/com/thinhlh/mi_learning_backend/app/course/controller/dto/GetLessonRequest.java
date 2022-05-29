package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetLessonRequest {
    String email;
    UUID lessonId;
}
