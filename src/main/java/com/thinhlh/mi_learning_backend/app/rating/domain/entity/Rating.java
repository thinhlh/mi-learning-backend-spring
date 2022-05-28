package com.thinhlh.mi_learning_backend.app.rating.domain.entity;

import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Table(name = "rating")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @Column(name = "id")
    private UUID id;

    @Lob
    @Column(name = "content")
    private String content;

    @Min(1)
    @Column(name = "value")
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentCourse studentCourse;
}
