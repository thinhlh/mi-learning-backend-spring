package com.thinhlh.mi_learning_backend.app.note.domain.entity;

import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Note {
    @Id
    private UUID id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentLesson studentLesson;
}
