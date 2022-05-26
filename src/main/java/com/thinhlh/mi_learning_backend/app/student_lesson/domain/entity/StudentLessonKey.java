package com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity;

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
public class StudentLessonKey implements Serializable {

    private UUID studentId;
    private UUID lessonId;
}
