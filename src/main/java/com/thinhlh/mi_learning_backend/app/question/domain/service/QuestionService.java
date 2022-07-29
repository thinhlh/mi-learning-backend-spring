package com.thinhlh.mi_learning_backend.app.question.domain.service;

import com.thinhlh.mi_learning_backend.app.question.domain.entities.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsOfLesson(String lessonId);
}
