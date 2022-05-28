package com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity;

import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;
import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student_lesson")
@Setter
public class StudentLesson {
    @EmbeddedId
    private StudentLessonKey id;

    @MapsId("studentId")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @MapsId("lessonId")
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @OneToMany(mappedBy = "studentLesson")
    private Set<Note> notes;

    private boolean finished;
}
