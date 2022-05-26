package com.thinhlh.mi_learning_backend.app.lession.domain.service;

import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonRequest;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;

import java.util.List;
import java.util.UUID;

public interface LessonService {

    List<Lesson> getAllLessonOfSection(UUID sectionId);

    Lesson createLesson(LessonRequest request);
}
