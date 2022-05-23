package com.thinhlh.mi_learning_backend.app.lession.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "video_lesson")
public class VideoLesson {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Lesson lesson;

    @Column(name = "video_url", nullable = false)
    @Getter
    private String videoUrl;

    @Column(name = "length", nullable = false)
    @Getter
    private LocalTime length;

}