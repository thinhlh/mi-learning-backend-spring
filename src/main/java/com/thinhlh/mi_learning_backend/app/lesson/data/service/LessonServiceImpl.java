package com.thinhlh.mi_learning_backend.app.lesson.data.service;

import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.*;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.LessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.StudentLessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.TestLessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.VideoLessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.TestLesson;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.VideoLesson;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.app.section.data.repository.SectionRepository;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final String LESSON_NOT_FOUND = "Lesson not found";
    private final String SECTION_NOT_FOUND = "Section not found";

    private final LessonRepository lessonRepository;
    private final VideoLessonRepository videoLessonRepository;
    private final TestLessonRepository testLessonRepository;
    private final SectionRepository sectionRepository;
    private final StudentLessonRepository studentLessonRepository;

    private final LessonMapper mapper;

    private final CourseService courseService;

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

    @Override
    @Transactional
    public LessonDetailResponse getLessonDetail(LessonDetailRequest request) {
        var lessonOptional = lessonRepository.findById(request.getLessonId());

        if (lessonOptional.isPresent()) {
            var lesson = lessonOptional.get();
            var studentLesson = studentLessonRepository.findByStudent_User_EmailAndLessonId(request.getEmail(), request.getLessonId());

            if (studentLesson != null) {
                var courseId = lesson.getSection().getCourse().getId();

                var lessonsInCourse = courseService.getLessonsInCourse(courseId);
                lessonsInCourse.sort(Comparator.comparing(Lesson::getLessonOrder));
                var lastViewedLesson = courseService.getLastFinishedLessonInCourse(courseId, request.getEmail());


                var response = LessonDetailResponse
                        .builder()
                        .courseId(lesson.getSection().getCourse().getId())
                        .sectionId(lesson.getSection().getId())
                        .lesson(lesson)
                        .notes(studentLesson.getNotes().stream().toList())
                        .finished(lastViewedLesson != null && lastViewedLesson.getLessonOrder() > lesson.getLessonOrder())
                        .build();

                return response;
            } else {
                throw new NotFoundException("Student not joined course");
            }

        } else {
            throw new NotFoundException(LESSON_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public Boolean updateLessonPlayback(UpdateLessonPlaybackRequest request) {
        var studentLesson = studentLessonRepository.findByStudent_User_EmailAndLessonId(request.getEmail(), request.getLessonId());

        if (studentLesson == null) {
            throw new NotFoundException("Student have not joined this lesson");
        } else {
            studentLesson.setPlayback(request.getPlayback());
        }

        return true;
    }
}
