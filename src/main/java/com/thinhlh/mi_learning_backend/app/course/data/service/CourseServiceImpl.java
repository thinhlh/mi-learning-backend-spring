package com.thinhlh.mi_learning_backend.app.course.data.service;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseMapper;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.MyCourseResponse;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.RecommendationCourseResponse;
import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.lession.data.repository.LessonRepository;
import com.thinhlh.mi_learning_backend.app.lession.data.repository.StudentLessonRepository;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.app.section.data.repository.SectionRepository;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.student_course.data.repository.StudentCourseRepository;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourseKey;
import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

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
    public List<CourseResponse> getExplorerCourses(String email) {
        return getAllCourses(email).stream().filter(courseResponse -> courseResponse.getEnrolled() == false).collect(Collectors.toList());
    }

    private CourseResponse getCourseResponse(Course course, String email) {
        var enrolled = studentCourseRepository.existsByStudent_User_EmailAndCourseId(email, course.getId());

        var finishedLesson = studentLessonRepository.findAllByStudent_User_EmailAndFinishedTrue(email);

        var finishedLessonOfCourse = finishedLesson.stream().filter(studentLesson ->
                studentLesson.getLesson().getSection().getCourse().getId().equals(course.getId())
        ).sorted(Comparator.comparing(o -> o.getLesson().getLessonOrder())).toList();

        var lastFinishedCourse = finishedLessonOfCourse.stream().toList().stream().findFirst().orElse(null);

        return mapper.toResponse(course, ratingService.getCourseRating(course.getId()), enrolled, lastFinishedCourse == null ? null : lastFinishedCourse.getLesson().getId());
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

    @Override
    @Transactional
    public List<RecommendationCourseResponse> getRecommendationCourses(String email) {
        var student = studentRepository.findByUser_Email(email);

        var coursesStudentEnrolled = courseRepository.findByStudents_Student_Id(student.getId());

        var coursesStudentNotEnrolled = courseRepository.findByIdIsNotIn(ListHelper.mapTo(coursesStudentEnrolled, Course::getId));

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
}
