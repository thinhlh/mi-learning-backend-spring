package com.thinhlh.mi_learning_backend.app.teacher.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherResponse {

    private UUID id;
    private String name;
    private String avatar;
}
