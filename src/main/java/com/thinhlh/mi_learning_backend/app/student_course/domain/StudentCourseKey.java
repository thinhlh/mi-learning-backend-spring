package com.thinhlh.mi_learning_backend.app.student_course.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class StudentCourseKey implements Serializable {

    private UUID studentId;
    private UUID courseId;


}
