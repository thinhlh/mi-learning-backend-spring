package com.thinhlh.mi_learning_backend.app.section.controller.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class SectionRequest {
    private UUID courseId;

    @NotBlank
    private String title;

    @Min(0)
    private Integer length;
}
