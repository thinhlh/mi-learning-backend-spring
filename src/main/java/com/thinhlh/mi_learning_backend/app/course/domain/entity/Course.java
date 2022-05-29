package com.thinhlh.mi_learning_backend.app.course.domain.entity;

import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "background", nullable = false)
    private String background;

    @Column(name = "icon")
    private String icon;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Section> sections;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @Getter(value = AccessLevel.NONE)
    private Set<StudentCourse> students = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}