package com.thinhlh.mi_learning_backend.app.lession.controller.dto;

import com.thinhlh.mi_learning_backend.app.lession.domain.entity.TestLesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.VideoLesson;
import lombok.Data;

import java.util.UUID;

@Data
public class LessonResponse {
    private UUID id;
    private String title;
    private VideoLessonResponse video;
    private TestLessonResponse test;
}
