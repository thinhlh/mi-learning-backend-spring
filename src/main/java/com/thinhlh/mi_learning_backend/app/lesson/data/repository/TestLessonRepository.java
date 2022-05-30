package com.thinhlh.mi_learning_backend.app.lesson.data.repository;

import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.TestLesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestLessonRepository extends CrudRepository<TestLesson, UUID> {
}
