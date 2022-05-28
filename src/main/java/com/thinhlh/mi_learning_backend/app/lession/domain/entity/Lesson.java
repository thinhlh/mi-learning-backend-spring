package com.thinhlh.mi_learning_backend.app.lession.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    private UUID id;

    @Column(nullable = false)
    @JsonIgnore
    private String title;

    private Integer lessonOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Section section;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private VideoLesson videoLesson;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private TestLesson testLesson;
}