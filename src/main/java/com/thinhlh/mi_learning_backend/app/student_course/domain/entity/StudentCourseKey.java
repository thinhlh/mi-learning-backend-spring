package com.thinhlh.mi_learning_backend.app.student_course.domain.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseKey implements Serializable {

    private UUID studentId;
    private UUID courseId;
}
