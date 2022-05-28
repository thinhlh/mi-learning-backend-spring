package com.thinhlh.mi_learning_backend.app.rating.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
