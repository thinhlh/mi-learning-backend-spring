package com.thinhlh.mi_learning_backend.app.student_course.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ToggleSaveCourseRequest {
    private String email;
    @NotNull
    private boolean saved;
    @NotNull
    private UUID courseId;
}
