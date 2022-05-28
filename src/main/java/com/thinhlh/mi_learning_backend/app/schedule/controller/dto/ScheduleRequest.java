package com.thinhlh.mi_learning_backend.app.schedule.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {


    private String email;

    @NotBlank
    private LocalDateTime dueDate;

    @NotBlank
    private String color;

    @NotBlank
    private String location;

    @NotBlank
    private String note;

    @NotBlank
    private String status;

    @NotBlank
    private String title;
}
