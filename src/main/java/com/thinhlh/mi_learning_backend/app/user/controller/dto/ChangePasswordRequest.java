package com.thinhlh.mi_learning_backend.app.user.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordRequest {
    private String email;

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
