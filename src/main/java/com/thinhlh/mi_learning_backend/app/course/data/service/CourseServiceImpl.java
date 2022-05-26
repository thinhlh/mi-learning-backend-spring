package com.thinhlh.mi_learning_backend.app.course.data.service;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseMapper;
import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.app.section.data.repository.SectionRepository;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.exceptions.ObjectAlreadyExistsException;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final SectionRepository sectionRepository;
    private final RatingService ratingService;
    private final CourseMapper mapper;

    @Override
    public List<CourseResponse> getAllCourses() {
        List<CourseResponse> courseResponses = new ArrayList<>();
        ListHelper.toList(repository.findAll()).forEach(course -> {
            courseResponses.add(mapper.toResponse(course, ratingService.getCourseRating(course.getId())));
        });

        return courseResponses;
    }

    @Override
    public Course createCourse(Course course) {
        return repository.save(course);
    }
}
