package com.thinhlh.mi_learning_backend.app.lesson.domain.service;

import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.*;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;

import java.util.List;
import java.util.UUID;

public interface LessonService {

    List<Lesson> getAllLessonOfSection(UUID sectionId);

    Lesson createLesson(LessonRequest request);

    LessonDetailResponse getLessonDetail(LessonDetailRequest request);

    Boolean updateLessonPlayback(UpdateLessonPlaybackRequest request);
}
