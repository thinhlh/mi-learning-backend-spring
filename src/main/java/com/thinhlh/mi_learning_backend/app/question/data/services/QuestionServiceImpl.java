package com.thinhlh.mi_learning_backend.app.question.data.services;

import com.thinhlh.mi_learning_backend.app.question.data.repositories.QuestionRepository;
import com.thinhlh.mi_learning_backend.app.question.domain.entities.Question;
import com.thinhlh.mi_learning_backend.app.question.domain.service.QuestionService;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;

    @Override
    public List<Question> getQuestionsOfLesson(String lessonId) {
        return ListHelper.toList(repository.getQuestionByLesson_Id(lessonId));
    }
}
