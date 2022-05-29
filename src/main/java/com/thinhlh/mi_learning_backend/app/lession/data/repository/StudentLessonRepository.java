package com.thinhlh.mi_learning_backend.app.lession.data.repository;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentLessonRepository extends CrudRepository<StudentLesson, UUID> {

    long countAllByStudent_IdAndLesson_IdAndFinishedIsTrue(UUID studentId, UUID lessonId);

    long countAllByStudent_User_EmailAndLesson_Section_CourseAndFinishedIsTrue(String email, Course course);

    long countAllByStudent_User_EmailAndFinishedIsTrueAndLesson_Id(String email, UUID lessonId);

    List<StudentLesson> findAllByStudent_User_EmailAndFinishedTrue(String email);

    long countByStudent_IdAndLesson_Section_Course(UUID id, Course course);
}
