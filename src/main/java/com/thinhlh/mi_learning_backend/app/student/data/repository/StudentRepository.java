package com.thinhlh.mi_learning_backend.app.student.data.repository;

import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {

    Student findByUser_Email(String email);

}
