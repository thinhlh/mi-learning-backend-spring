package com.thinhlh.mi_learning_backend.app.student.domain.entity;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.student_course.domain.StudentCourse;
import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student")
    private User user;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<StudentCourse> courses = new LinkedHashSet<>();
}