package com.thinhlh.mi_learning_backend.app.student.domain.entity;

import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.Schedule;
import com.thinhlh.mi_learning_backend.app.student_course.domain.entity.StudentCourse;
import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "student")
@Setter
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student")
    @Getter
    private User user;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @Getter
    private Set<StudentCourse> courses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @Getter
    private Set<Schedule> schedules = new LinkedHashSet<>();
}