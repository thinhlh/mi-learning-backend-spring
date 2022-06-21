package com.thinhlh.mi_learning_backend.app.note.domain.entity;

import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Note {
    @Id
    private UUID id;

    @Lob
    private String content;

    private int createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(value = AccessLevel.NONE)
    private StudentLesson studentLesson;
}
