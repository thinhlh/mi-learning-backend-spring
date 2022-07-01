package com.thinhlh.mi_learning_backend.app.student_lesson.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;
import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student_lesson")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentLesson {
    @EmbeddedId
    @JsonIgnore
    private StudentLessonKey id;

    @MapsId("studentId")
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @MapsId("lessonId")
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;

    @OneToMany(mappedBy = "studentLesson")
    private List<Note> notes;

    @Column(columnDefinition = "boolean default false")
    private boolean finished;

    private Integer playback;
}
