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
public class UpdateLessonPlaybackRequest {
    private UUID lessonId;
    private Integer playback;
    private String email;
}
