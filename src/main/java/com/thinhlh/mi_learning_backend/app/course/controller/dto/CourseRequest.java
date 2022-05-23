package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CourseRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Min(value = 0)
    private Integer hours;

    @NotBlank
    @URL
    private String background;

    @NotBlank
    @URL
    private String icon;

    @Min(0)
    private Double price;
}
