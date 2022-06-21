package com.thinhlh.mi_learning_backend.app.lesson.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteRequest {
    private UUID lessonId;
    private String email;
    private String content;
    private int createdAt;
    private UUID id;
}
