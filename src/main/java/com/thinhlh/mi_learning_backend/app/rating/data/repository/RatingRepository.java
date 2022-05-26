package com.thinhlh.mi_learning_backend.app.rating.data.repository;

import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends CrudRepository<Rating, UUID> {
    List<Rating> findByStudentCourse_Id_CourseId(UUID courseId);
}
