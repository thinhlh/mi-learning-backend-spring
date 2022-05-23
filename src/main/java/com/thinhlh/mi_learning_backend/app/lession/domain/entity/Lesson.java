package com.thinhlh.mi_learning_backend.app.lession.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @Column(name = "title", nullable = false)
    @Getter
    private String title;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "lesson")
    @Getter
    private VideoLesson videoLesson;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "lesson")
    @Getter
    private TestLesson testLesson;
}