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
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    private CourseResponse getCourseResponse(Course course, String email) {
        var enrolled = studentCourseRepository.existsByStudent_User_EmailAndCourseId(email, course.getId());
        return mapper.toResponse(course, ratingService.getCourseRating(course.getId()), enrolled);
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
            AtomicLong totalLesson = new AtomicLong();
            AtomicLong lessonFinished = new AtomicLong();

            var sections = sectionRepository.findAllByCourse_Id(course.getId());

            sections.forEach(section -> {
                section.getLessons().forEach(lesson -> {
                    totalLesson.addAndGet(1);
                    lessonFinished.addAndGet(studentLessonRepository.countAllByStudent_User_EmailAndFinishedIsTrue(email));
                });
            });

            result.add(mapper.toMyCourseResponse(course, totalLesson.get(), lessonFinished.get(), "Last lesson"));
        });

        return result;
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
