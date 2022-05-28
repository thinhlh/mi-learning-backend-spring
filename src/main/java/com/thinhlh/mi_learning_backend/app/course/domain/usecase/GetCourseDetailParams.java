package com.thinhlh.mi_learning_backend.app.course.domain.usecase;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class GetCourseDetailParams {
    private UUID courseId;
    private String email;
}
