package com.thinhlh.mi_learning_backend.app.note.domain.entity;

import com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity.StudentLesson;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Note {
    @Id
    private UUID id;

    private String content;

    private int createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentLesson studentLesson;
}
