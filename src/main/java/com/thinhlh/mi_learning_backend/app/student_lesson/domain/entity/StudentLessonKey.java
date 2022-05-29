package com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class StudentLessonKey implements Serializable {

    private UUID studentId;
    private UUID lessonId;
}
