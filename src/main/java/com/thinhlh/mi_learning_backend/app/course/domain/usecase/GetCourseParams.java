package com.thinhlh.mi_learning_backend.app.course.domain.usecase;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.GetCourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseParams {
    private String email;
    private GetCourseType type;
}
