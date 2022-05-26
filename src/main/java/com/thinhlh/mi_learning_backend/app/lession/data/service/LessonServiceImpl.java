package com.thinhlh.mi_learning_backend.app.lession.data.service;

import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonMapper;
import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonRequest;
import com.thinhlh.mi_learning_backend.app.lession.data.repository.LessonRepository;
import com.thinhlh.mi_learning_backend.app.lession.data.repository.TestLessonRepository;
import com.thinhlh.mi_learning_backend.app.lession.data.repository.VideoLessonRepository;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.TestLesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.VideoLesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.app.section.data.repository.SectionRepository;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final String SECTION_NOT_FOUND = "Section not found";

    private final LessonRepository lessonRepository;
    private final VideoLessonRepository videoLessonRepository;
    private final TestLessonRepository testLessonRepository;
    private final SectionRepository sectionRepository;

    private final LessonMapper mapper;

    @Override
    public List<Lesson> getAllLessonOfSection(UUID sectionId) {
        return lessonRepository.findAllBySection_Id(sectionId);
    }

    @Override
    @Transactional
    public Lesson createLesson(LessonRequest request) {

        var lesson = mapper.toLesson(request);

        var sectionId = request.getSectionId();

        var section = sectionRepository.findById(sectionId);

        if (section.isPresent()) {
            lesson.setSection(section.get());


            if (request.isVideo()) {
                var videoLesson = new VideoLesson();
                videoLesson.setId(lesson.getId());
                videoLesson.setLength(request.getLength());
                videoLesson.setVideoUrl(request.getVideoUrl());

                lesson.setVideoLesson(videoLesson);
                videoLessonRepository.save(videoLesson);
            } else {
                var testLesson = new TestLesson();
                testLesson.setId(lesson.getId());
                testLesson.setLesson(lesson);

                lesson.setTestLesson(testLesson);
                testLessonRepository.save(testLesson);
            }

            lesson = lessonRepository.save(lesson);
        } else {
            throw new NotFoundException(SECTION_NOT_FOUND);
        }

        return lesson;
    }
}
