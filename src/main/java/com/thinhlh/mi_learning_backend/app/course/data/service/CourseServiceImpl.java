package com.thinhlh.mi_learning_backend.app.course.data.service;

import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.exceptions.ObjectAlreadyExistsException;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    @Override
    public List<Course> getAllCourses() {
        return ListHelper.toList(repository.findAll());
    }

    @Override
    public Course createCourse(Course course) {
        if (repository.existsById(course.getId())) {
            throw new ObjectAlreadyExistsException("Course already existed");
        }

        return repository.save(course);
    }
}
