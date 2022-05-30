package com.thinhlh.mi_learning_backend.app.course.data.service;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseMapper;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.MyCourseResponse;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.RecommendationCourseResponse;
import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.LessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.data.repository.StudentLessonRepository;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.app.section.data.repository.SectionRepository;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.student_course.data.repository.StudentCourseRepository;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final String COURSE_NOT_FOUND = "Course not found";

    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final StudentLessonRepository studentLessonRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final RatingService ratingService;
    private final CourseMapper mapper;

    @Override
    @Transactional
    public List<CourseResponse> getAllCourses(String email) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        ListHelper.toList(courseRepository.findAll()).forEach(course -> {
            courseResponses.add(getCourseResponse(course, email));
        });

        return courseResponses;
    }

    @Override
    @Transactional
    public List<CourseResponse> getSavedCourses(String email) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        ListHelper.toList(courseRepository.findAll()).forEach(course -> {
            if (checkStudentEnrolledCourse(email, course.getId()))
                courseResponses.add(getCourseResponse(course, email));
        });

        return courseResponses;
    }

    @Override
    @Transactional
    public List<CourseResponse> getExplorerCourses(String email) {
        return getAllCourses(email).stream().filter(courseResponse -> !courseResponse.getEnrolled()).collect(Collectors.toList());
    }

    private CourseResponse getCourseResponse(Course course, String email) {

        var enrolled = checkStudentEnrolledCourse(email, course.getId());
        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(email, course.getId());
        var saved = studentCourse != null && studentCourse.isSaved();

        return mapper.toResponse(
                course,
                ratingService.getCourseRating(
                        course.getId()
                ),
                enrolled,
                enrolled ? getLastFinishedLessonInCourse(course.getId(), email).getId() : null,
                saved
        );
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public List<MyCourseResponse> getMyCourses(String email) {
        List<MyCourseResponse> result = new ArrayList<>();
        var enrolledCourses = studentCourseRepository.findAllByStudent_User_Email(email);

        enrolledCourses.stream().map(StudentCourse::getCourse).forEach(course -> {
            var lessonFinishedAndTotalLesson = getLessonFinishedAndTotalLesson(course, email);

            result.add(
                    mapper.toMyCourseResponse(
                            course,
                            lessonFinishedAndTotalLesson.getFirst(),
                            lessonFinishedAndTotalLesson.getSecond(),
                            "Last lesson")
            );
        });

        return result;
    }


    private Pair<Long, Long> getLessonFinishedAndTotalLesson(Course course, String email) {
        AtomicLong totalLesson = new AtomicLong();
        AtomicLong lessonFinished = new AtomicLong();

        var sections = sectionRepository.findAllByCourse_Id(course.getId());

        var lessonsFinishedIds = studentLessonRepository.findAllByStudent_User_EmailAndFinishedTrue(email).stream().map(studentLesson -> studentLesson.getLesson().getId()).toList();

        sections.forEach(section -> {
            section.getLessons().forEach(lesson -> {
                totalLesson.addAndGet(1);
                if (lessonsFinishedIds.contains(lesson.getId())) {
                    lessonFinished.addAndGet(1);
                }
            });
        });

        return Pair.of(lessonFinished.get(), totalLesson.get());
    }

    private boolean checkStudentEnrolledCourse(String email, UUID courseId) {
        var studentCourse = studentCourseRepository.findByStudent_User_EmailAndCourse_Id(email, courseId);

        return studentCourse != null && studentCourse.isEnrolled();
    }

    @Override
    @Transactional
    public List<RecommendationCourseResponse> getRecommendationCourses(String email) {
        var student = studentRepository.findByUser_Email(email);

        var coursesStudentEnrolled = ListHelper.toList(
                courseRepository.findAll()).stream().filter(
                course -> checkStudentEnrolledCourse(email, course.getId())
        ).toList().stream().map(Course::getId).toList();

//        var coursesStudentNotEnrolled = courseRepository.findByIdNotIn(ListHelper.mapTo(coursesStudentEnrolled, Course::getId));

        var allCourse = ListHelper.toList(courseRepository.findAll());
        var coursesStudentNotEnrolled = new ArrayList<Course>();

        allCourse.forEach(course -> {
            if (!coursesStudentEnrolled.contains(course.getId())) {
                coursesStudentNotEnrolled.add(course);
            }
        });


        var result = new ArrayList<RecommendationCourseResponse>();

        coursesStudentNotEnrolled.forEach(course -> {
            var teacherName = course.getTeacher().getUser().getName();
            var rating = ratingService.getCourseRating(course.getId()).getAverage();
            result.add(mapper.toRecommendation(course, teacherName, rating));
        });

        return result;
    }

    @Override
    @Transactional
    public CourseResponse getCourseDetail(UUID courseId, String email) {
        var course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            course.get().getSections();
            return getCourseResponse(course.get(), email);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Lesson> getLessonInCourse(UUID courseId) {
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
        var lessonsInCourse = getLessonInCourse(courseId);
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
