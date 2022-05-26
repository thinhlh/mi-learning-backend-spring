package com.thinhlh.mi_learning_backend.app.category.controller.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank
    private String title;
}
