package com.thinhlh.mi_learning_backend.app.rating.controller.dto;

import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
    private UUID id;
    private String content;
    private Integer value;
    private String name;
    private String avatar;
}
