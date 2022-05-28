package com.thinhlh.mi_learning_backend.app.lession.data.repository;

import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentLessonRepository extends CrudRepository<StudentLesson, UUID> {

    long countAllByStudent_IdAndLesson_IdAndFinishedIsTrue(UUID studentId, UUID lessonId);

    long countAllByStudent_User_EmailAndFinishedIsTrue(String email);

}
