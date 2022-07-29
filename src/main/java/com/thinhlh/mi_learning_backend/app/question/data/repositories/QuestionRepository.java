package com.thinhlh.mi_learning_backend.app.question.data.repositories;

import com.thinhlh.mi_learning_backend.app.question.domain.entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends CrudRepository<Question, UUID> {
    List<Question> getQuestionByLesson_Id(String lessonId);
}
