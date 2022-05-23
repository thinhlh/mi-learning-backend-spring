package com.thinhlh.mi_learning_backend.app.section.data.service;

import com.thinhlh.mi_learning_backend.app.course.data.repository.CourseRepository;
import com.thinhlh.mi_learning_backend.app.section.controller.dto.SectionMapper;
import com.thinhlh.mi_learning_backend.app.section.controller.dto.SectionRequest;
import com.thinhlh.mi_learning_backend.app.section.data.repository.SectionRepository;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.section.domain.service.SectionService;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final String COURSE_NOT_FOUND = "Course not found";

    @Getter
    @Setter
    @Value("{env.dev}")
    private String env;

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final SectionMapper mapper;

    @Override
    public List<Section> getAllSections(UUID courseId) {
        return sectionRepository.findAllByCourse_Id(courseId);
    }

    @Override
    public Section createSection(SectionRequest request) {
        var section = mapper.toSection(request);

        var course = courseRepository.findById(request.getCourseId());

        if (course.isPresent()) {
            section.setCourse(course.get());
            return sectionRepository.save(section);
        } else {
            throw new NotFoundException(COURSE_NOT_FOUND);
        }
    }
}
