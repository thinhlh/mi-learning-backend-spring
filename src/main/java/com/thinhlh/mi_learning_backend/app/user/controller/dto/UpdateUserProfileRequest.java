package com.thinhlh.mi_learning_backend.app.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserProfileRequest {
    private String email;
    private String name;
    private String avatar;
    private String occupation;
    private String birthday;
}
