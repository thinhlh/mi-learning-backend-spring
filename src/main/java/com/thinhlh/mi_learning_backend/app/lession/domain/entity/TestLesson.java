package com.thinhlh.mi_learning_backend.app.lession.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "test_lesson")
@Getter
public class TestLesson {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Lesson lesson;
}