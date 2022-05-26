package com.thinhlh.mi_learning_backend.app.lession.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "lesson")
@Getter
@Setter
public class Lesson {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(value = AccessLevel.NONE)
    private Section section;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "video_lesson_id")
    private VideoLesson videoLesson;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "test_lesson_id")
    private TestLesson testLesson;
}