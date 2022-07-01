package com.thinhlh.mi_learning_backend.app.student_course.data.service;

import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonDetailRequest;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.StudentLessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.*;
import com.thinhlh.mi_learning_backend.app.student_course.data.repository.StudentCourseRepository;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourseKey;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.app.student_course.domain.usecase.GetStudentCourseDetailParams;
import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;
import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLessonKey;
import com.thinhlh.mi_learning_backend.app.user.data.repository.UserRepository;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.exceptions.ObjectAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentLessonRepository studentLessonRepository;
    private final CourseService courseService;
    private final LessonService lessonService;

    private final StudentCourseMapper mapper;

    @Override
    @Transactional
    public Boolean studentJoinCourse(String email, UUID courseId) {

        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(email, courseId);

        if (studentCourse != null && studentCourse.isEnrolled()) {
            throw new ObjectAlreadyExistsException();
        } else {

            var course = courseRepository.findById(courseId);
            var student = studentRepository.findByUser_Email(email);

            studentCourse = createStudentCourseIfNotExists(email, courseId);
            studentCourse.setEnrolled(true);

            course.get().getSections().forEach(section -> {

                studentLessonRepository.saveAll(section.getLessons().stream().map(lesson -> {
                    var studentLessonKey = new StudentLessonKey(student.getId(), lesson.getId());

                    return StudentLesson.builder()
                            .id(studentLessonKey)
                            .student(student)
                            .lesson(lesson)
                            .finished(false)
                            .playback(0)
                            .build();
                }).collect(Collectors.toList()));
            });
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean checkStudentEnrolledCourse(String email, UUID courseId) {
        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(email, courseId);

        return studentCourse != null && studentCourse.isEnrolled();
    }

    @Override
    @Transactional
    public StudentCourseResponse getStudentCourseDetail(GetStudentCourseDetailParams params) {

        var courseOptional = courseRepository.findById(params.getCourseId());
        if (!checkStudentEnrolledCourse(params.getEmail(), params.getCourseId())) {
            throw new NotFoundException("Student have not joined course");
        }
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        var course = courseOptional.get();
        var lastViewedLessonInCourse = courseService.getLastFinishedLessonInCourse(course.getId(), params.getEmail());
        return StudentCourseResponse
                .builder()
                .courseId(params.getCourseId())
                .length(course.getLength())
                .currentLesson(lastViewedLessonInCourse == null ? null : lastViewedLessonInCourse.getId())
                .sections(course.getSections().stream().map(section -> {
                    AtomicInteger totalLesson = new AtomicInteger(0);
                    AtomicInteger finishedLesson = new AtomicInteger(0);
                    AtomicLong length = new AtomicLong(0);

                    return StudentCourseSectionResponse.builder()
                            .id(section.getId())
                            .title(section.getTitle())
                            .lessons(section.getLessons().stream().sorted(Comparator.comparing(Lesson::getLessonOrder)).map(lesson -> {
                                var lessonDetail = lessonService.getLessonDetail(new LessonDetailRequest(params.getEmail(), lesson.getId()));
                                if (lesson.getVideoLesson() != null) {
                                    length.addAndGet(lesson.getVideoLesson().getLength());
                                }
                                totalLesson.addAndGet(1);
                                if (lessonDetail.isFinished()) finishedLesson.addAndGet(1);
                                return StudentCourseLessonResponse
                                        .builder()
                                        .id(lesson.getId())
                                        .lessonOrder(lesson.getLessonOrder())
                                        .testLesson(lesson.getTestLesson())
                                        .videoLesson(lesson.getVideoLesson())
                                        .title(lesson.getTitle())
                                        .metadata(StudentCourseMetaDataResponse
                                                .builder()
                                                .finished(lessonDetail.isFinished())
                                                .notes(lessonDetail.getNotes())
                                                .build())
                                        .build();
                            }).collect(Collectors.toList()))
                            .totalLesson(totalLesson.get())
                            .finishedLesson(finishedLesson.get())
                            .length(length.get())
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    StudentCourse createStudentCourseIfNotExists(String email, UUID courseId) {
        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(email, courseId);

        if (studentCourse == null) {
            var student = studentRepository.findByUser_Email(email);
            if (student == null) {
                throw new NotFoundException();

            } else {
                var course = courseRepository.findById(courseId);
                if (course.isEmpty()) {
                    throw new NotFoundException();
                } else {
                    var key = new StudentCourseKey(student.getId(), courseId);
                    studentCourse = new StudentCourse();

                    studentCourse.setId(key);
                    studentCourse.setCourse(course.get());
                    studentCourse.setStudent(student);

                    studentCourse = studentCourseRepository.save(studentCourse);
                }
            }
        }
        return studentCourse;
    }

    @Override
    @Transactional
    public Boolean toggleSavedCourse(ToggleSaveCourseRequest request) {
        var studentCourse = createStudentCourseIfNotExists(request.getEmail(), request.getCourseId());

        studentCourse.setSaved(request.isSaved());

        return studentCourseRepository.save(studentCourse).isSaved();
    }
}
