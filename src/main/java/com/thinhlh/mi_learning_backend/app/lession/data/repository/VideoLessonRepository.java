package com.thinhlh.mi_learning_backend.app.lession.data.repository;

import com.thinhlh.mi_learning_backend.app.lession.domain.entity.VideoLesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoLessonRepository extends CrudRepository<VideoLesson, UUID> {
}
