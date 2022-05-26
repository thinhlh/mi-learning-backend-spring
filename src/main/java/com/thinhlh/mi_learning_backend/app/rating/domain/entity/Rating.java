package com.thinhlh.mi_learning_backend.app.rating.domain.entity;

import com.thinhlh.mi_learning_backend.app.student_course.domain.StudentCourse;
import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Table(name = "rating")
@Setter
@Getter
public class Rating {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "content")
    private String content;

    @Min(1)
    @Column(name = "value")
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(value = AccessLevel.NONE)
    private StudentCourse studentCourse;
}
