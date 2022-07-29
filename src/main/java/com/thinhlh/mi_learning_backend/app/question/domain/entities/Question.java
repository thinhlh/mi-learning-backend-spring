package com.thinhlh.mi_learning_backend.app.question.domain.entities;

import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.TestLesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    private UUID id;

    private String question;

    private String thumbnail;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne
    private TestLesson lesson;
}
