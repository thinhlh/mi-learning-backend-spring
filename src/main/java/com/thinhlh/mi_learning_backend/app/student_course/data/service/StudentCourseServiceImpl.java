package com.thinhlh.mi_learning_backend.app.student_course.data.service;

import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.student_course.data.repository.StudentCourseRepository;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourseKey;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.app.user.data.repository.UserRepository;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.exceptions.ObjectAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
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
                }
            }
        }

        return true;
    }
}
