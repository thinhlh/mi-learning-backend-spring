package com.thinhlh.mi_learning_backend.app.lession.data.repository;

import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends CrudRepository<Lesson, UUID> {

    List<Lesson> findAllBySection_Id(UUID section_id);

    long countAllBySection_Id(UUID sectionId);

}
