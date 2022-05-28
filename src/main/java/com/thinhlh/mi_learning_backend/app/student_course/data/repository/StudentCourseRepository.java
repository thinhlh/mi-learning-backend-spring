package com.thinhlh.mi_learning_backend.app.student_course.data.repository;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourseKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, StudentCourseKey> {
    StudentCourse findByStudent_IdAndCourse_Id(UUID studentId, UUID courseId);

    StudentCourse findByStudent_User_EmailAndCourse_Id(String email, UUID courseId);

    boolean existsByStudent_User_EmailAndCourseId(String email, UUID courseId);

    List<StudentCourse> findAllByStudent_User_Email(String email);

}
