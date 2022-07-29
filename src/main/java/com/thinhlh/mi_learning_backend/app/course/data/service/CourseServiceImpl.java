package com.thinhlh.mi_learning_backend.app.course.data.service;

import com.thinhlh.mi_learning_backend.app.category.domain.service.CategoryService;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseMapper;
import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.GetCourseDetailParams;
import com.thinhlh.mi_learning_backend.app.course.domain.usecase.GetCourseParams;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.CourseResponseV2;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonDetailRequest;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonDetailResponse;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.LessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.StudentLessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseLessonResponse;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseMetaDataResponse;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseSectionResponse;
import com.thinhlh.mi_learning_backend.app.student_course.data.repository.StudentCourseRepository;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final String COURSE_NOT_FOUND = "Course not found";

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final StudentLessonRepository studentLessonRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final RatingService ratingService;

    private final CategoryService categoryService;
    private final CourseMapper mapper;

    @Override
    @Transactional
    public List<CourseResponseV2> getCourses(GetCourseParams params) {
        var allCourses =
                ListHelper
                        .toList(
                                courseRepository
                                        .findAll())
                        .stream()
                        .map(course -> getCourseDetail(new GetCourseDetailParams(course.getId(), params.getEmail())))
                        .toList();
        var filterCourses = new ArrayList<CourseResponseV2>();
        if (params.getType() != null) {
            filterCourses.addAll(switch (params.getType()) {
                case ALL -> allCourses;

                case ME -> allCourses
                        .stream()
                        .filter(it -> checkStudentEnrolledCourse(params.getEmail(), it.getId()))
                        .toList();
                case FOR_YOU -> allCourses
                        .stream()
                        .filter(it -> !checkStudentEnrolledCourse(params.getEmail(), it.getId()))
                        .toList();
                case SAVED -> allCourses
                        .stream()
                        .filter(it -> checkCourseSaved(params.getEmail(), it.getId()))
                        .toList();
                case TOP_CHARTS -> allCourses
                        .stream()
                        .sorted((o1, o2) ->
                                o2
                                        .getCourseRatings()
                                        .getAverage()
                                        .compareTo(o1
                                                .getCourseRatings()
                                                .getAverage())
                        ).toList();

                case TEACHER_CHOICE -> {
                    var notEnrolledCourses = new ArrayList<CourseResponseV2>() {{
                        addAll(allCourses
                                .stream()
                                .filter(it -> !checkStudentEnrolledCourse(params.getEmail(), it.getId()))
                                .toList());
                    }};
                    Collections.shuffle(notEnrolledCourses);

                    yield notEnrolledCourses;

                }
            });
        }

        if (params.getCategoryId() != null) {
            var category = categoryService.getCategoryById(params.getCategoryId());
            filterCourses = allCourses.stream().filter((it) -> it.getCategory().equals(category.getTitle())).collect(Collectors.toCollection(ArrayList::new));
        }

        return filterCourses;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    private boolean checkStudentEnrolledCourse(String email, UUID courseId) {
        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(email, courseId);

        return studentCourse != null && studentCourse.isEnrolled();
    }

    @Override
    @Transactional
    public CourseResponseV2 getCourseDetail(GetCourseDetailParams params) {
        var course = courseRepository.findById(params.getCourseId()).orElse(null);
        if (course == null) {
            throw new NotFoundException(COURSE_NOT_FOUND);
        } else {
            var courseResponse = mapper.toCourseResponseV2(course);

            var email = params.getEmail();
            var enrolled = checkStudentEnrolledCourse(email, course.getId());
            var saved = checkCourseSaved(email, course.getId());

            courseResponse.setEnrolled(enrolled);
            courseResponse.setSaved(saved);

            var courseRatings = ratingService.getCourseRating(course.getId());
            courseResponse.setCourseRatings(courseRatings);

            var lesson = getLastFinishedLessonInCourse(course.getId(), email);
            courseResponse.setCurrentLesson(lesson == null ? null : lesson.getId());

            courseResponse.setSections(getStudentCourseSectionResponse(course, email));

            return courseResponse;
        }
    }

    private Boolean checkCourseSaved(String email, UUID courseId) {
        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(email, courseId);

        return studentCourse != null && studentCourse.isSaved();
    }

    private List<StudentCourseSectionResponse> getStudentCourseSectionResponse(Course course, String email) {
        return course.getSections().stream().map(section -> {
            AtomicInteger totalLesson = new AtomicInteger(0);
            AtomicInteger finishedLesson = new AtomicInteger(0);
            AtomicLong length = new AtomicLong(0);

            return StudentCourseSectionResponse.builder()
                    .id(section.getId())
                    .title(section.getTitle())
                    .lessons(section.getLessons().stream().sorted(Comparator.comparing(Lesson::getLessonOrder)).map(lesson -> {
                        var lessonDetail = getLessonDetail(new LessonDetailRequest(email, lesson.getId()));

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
                                        .playback(lessonDetail.getPlayback())
                                        .build())
                                .build();
                    }).collect(Collectors.toList()))
                    .totalLesson(totalLesson.get())
                    .finishedLesson(finishedLesson.get())
                    .length(length.get())
                    .build();
        }).toList();
    }

    private LessonDetailResponse getLessonDetail(LessonDetailRequest request) {
        var lessonOptional = lessonRepository.findById(request.getLessonId());

        if (lessonOptional.isPresent()) {
            var lesson = lessonOptional.get();
            var studentLesson = studentLessonRepository.findByStudent_User_EmailAndLessonId(request.getEmail(), request.getLessonId());

            if (studentLesson != null) {
                var courseId = lesson.getSection().getCourse().getId();

                var lessonsInCourse = getLessonsInCourse(courseId);
                lessonsInCourse.sort(Comparator.comparing(Lesson::getLessonOrder));
                var lastViewedLesson = getLastFinishedLessonInCourse(courseId, request.getEmail());

                var response = LessonDetailResponse
                        .builder()
                        .courseId(lesson.getSection().getCourse().getId())
                        .sectionId(lesson.getSection().getId())
                        .lesson(lesson)
                        .playback(0)
                        .notes(studentLesson.getNotes().stream().toList())
                        .finished(studentLesson.isFinished())
                        .build();

                return response;
            } else {
                return LessonDetailResponse
                        .builder()
                        .courseId(lesson.getSection().getCourse().getId())
                        .sectionId(lesson.getSection().getId())
                        .lesson(lesson)
                        .playback(0)
                        .notes(new ArrayList<>())
                        .finished(false)
                        .build();
            }

        } else {
            throw new NotFoundException("Lesson not found.");
        }
    }

    @Override
    public List<Lesson> getLessonsInCourse(UUID courseId) {
        var lessons = new ArrayList<Lesson>();

        var courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            var course = courseOpt.get();
            var sections = course.getSections();
            sections.forEach(section -> lessons.addAll(section.getLessons()));
            lessons.sort(Comparator.comparing(Lesson::getLessonOrder));

            return lessons;
        } else {
            throw new NotFoundException(COURSE_NOT_FOUND);
        }
    }

    @Override
    public Lesson getLastFinishedLessonInCourse(UUID courseId, String email) {
        var lessonsInCourse = getLessonsInCourse(courseId);
        AtomicReference<Lesson> lastFinishedLesson = new AtomicReference<>(null);

        lessonsInCourse.forEach(lesson -> {
            var studentLesson = studentLessonRepository.findByStudent_User_EmailAndLessonId(email, lesson.getId());

            if (studentLesson != null) {
                if (studentLesson.isFinished()) {
                    lastFinishedLesson.set(lesson);
                }
            }
        });

        return lastFinishedLesson.get();
    }
}
