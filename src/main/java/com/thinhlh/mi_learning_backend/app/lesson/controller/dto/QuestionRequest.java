package com.thinhlh.mi_learning_backend.app.lesson.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class QuestionRequest {
    private String question;
    private String thumbnail;

    private List<AnswerRequest> answers;

    @Builder
    @Data
    public static class AnswerRequest {
        private String content;
        private boolean isCorrect;
    }
}
