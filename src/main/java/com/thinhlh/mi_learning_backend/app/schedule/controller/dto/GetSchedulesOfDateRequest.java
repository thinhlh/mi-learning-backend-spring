package com.thinhlh.mi_learning_backend.app.schedule.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSchedulesOfDateRequest {
    private String email;

    @NotNull
    private LocalDate date;
}
