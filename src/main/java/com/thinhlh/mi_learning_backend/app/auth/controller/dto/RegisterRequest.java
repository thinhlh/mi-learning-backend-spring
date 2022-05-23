package com.thinhlh.mi_learning_backend.app.auth.controller.dto;

import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Email
    private String email;

    @NotBlank
    private String occupation;

    @Past
    private LocalDate birthday;

    @NotBlank
    private String role;
}
