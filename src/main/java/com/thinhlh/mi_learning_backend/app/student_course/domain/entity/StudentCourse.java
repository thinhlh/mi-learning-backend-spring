package com.thinhlh.mi_learning_backend.app.student_course.domain.entity;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.rating.domain.entity.Rating;
import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class StudentCourse {

    @EmbeddedId
    private StudentCourseKey id;

    @MapsId(value = "studentId")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @MapsId(value = "courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentCourse")
    private Set<Rating> ratings;

    @Column(columnDefinition = "boolean default false")
    private boolean saved;

    @Column(columnDefinition = "boolean default false")
    private boolean enrolled;
}
