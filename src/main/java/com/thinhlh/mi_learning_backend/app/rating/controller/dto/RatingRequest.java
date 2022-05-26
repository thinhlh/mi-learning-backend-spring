package com.thinhlh.mi_learning_backend.app.rating.controller.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class RatingRequest {

    @NotNull
    private UUID courseId;

    @NotNull
    private String content;

    @Valid
    private Integer value;

    @NotNull
    private UUID studentId;

}
