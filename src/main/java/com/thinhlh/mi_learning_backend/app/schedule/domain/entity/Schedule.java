package com.thinhlh.mi_learning_backend.app.schedule.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    private UUID id;

    private String title;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "note")
    private String note;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @JoinColumn(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private ScheduleColor color;

    private String location;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;
}
