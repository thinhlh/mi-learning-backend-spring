package com.thinhlh.mi_learning_backend.app.teacher.data.repository;

import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
    Teacher findByUser_Email(String email);
}
