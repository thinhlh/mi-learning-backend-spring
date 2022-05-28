package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import com.thinhlh.mi_learning_backend.app.rating.controller.dto.CourseRatingResponse;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.teacher.controller.dto.TeacherResponse;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CourseResponse {
    private UUID id;
    private String title;
    private String description;
    private Integer length;
    private String background;
    private String icon;
    private Double price;
    private String category;
    private Boolean enrolled;

    private TeacherResponse teacher;

    private List<Section> sections;

    private CourseRatingResponse courseRatings;
}
