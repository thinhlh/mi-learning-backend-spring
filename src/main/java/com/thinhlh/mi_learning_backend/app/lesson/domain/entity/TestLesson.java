package com.thinhlh.mi_learning_backend.app.lesson.domain.entity;

import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "test_lesson")
@Setter
public class TestLesson {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(mappedBy = "testLesson", fetch = FetchType.LAZY, optional = false)
    private Lesson lesson;
}