package com.thinhlh.mi_learning_backend.app.course.data.repository;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CourseRepository extends CrudRepository<Course, UUID> {
}
