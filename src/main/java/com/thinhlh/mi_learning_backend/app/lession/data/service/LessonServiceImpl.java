package com.thinhlh.mi_learning_backend.app.lession.data.service;

import com.thinhlh.mi_learning_backend.app.lession.data.repository.LessonRepository;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> getAllLessonOfSection(UUID sectionId) {
        return lessonRepository.findAllBySection_Id(sectionId);
    }
}
