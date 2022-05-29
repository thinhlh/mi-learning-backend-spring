package com.thinhlh.mi_learning_backend.app.student_course.data.service;

import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.lession.data.repository.StudentLessonRepository;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.student_course.data.repository.StudentCourseRepository;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourseKey;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;
import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLessonKey;
import com.thinhlh.mi_learning_backend.app.user.data.repository.UserRepository;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.exceptions.ObjectAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentLessonRepository studentLessonRepository;

    @Override
    @Transactional
    public Boolean studentJoinCourse(String email, UUID courseId) {

        var userEnrolled = studentCourseRepository.existsByStudent_User_EmailAndCourseId(email, courseId);

        if (userEnrolled) {
            throw new ObjectAlreadyExistsException();
        } else {
            var student = studentRepository.findByUser_Email(email);
            if (student == null) {
                throw new NotFoundException();

            } else {
                var course = courseRepository.findById(courseId);
                if (course.isEmpty()) {
                    throw new NotFoundException();
                } else {
                    var key = new StudentCourseKey(student.getId(), courseId);
                    var studentCourse = new StudentCourse();

                    studentCourse.setId(key);
                    studentCourse.setCourse(course.get());
                    studentCourse.setStudent(student);

                    studentCourseRepository.save(studentCourse);

                    course.get().getSections().forEach(section -> {

                        studentLessonRepository.saveAll(section.getLessons().stream().map(lesson -> {
                            var studentLessonKey = new StudentLessonKey(student.getId(), lesson.getId());

                            return StudentLesson.builder()
                                    .id(studentLessonKey)
                                    .student(student)
                                    .lesson(lesson)
                                    .finished(new Random().nextBoolean())
                                    .build();
                        }).collect(Collectors.toList()));
                    });
                }
            }
        }

        return true;
    }
}
