package com.thinhlh.mi_learning_backend.app.course.domain.entity;

import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
@Getter
@Setter
public class Course {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

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

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new LinkedHashSet<>();
}