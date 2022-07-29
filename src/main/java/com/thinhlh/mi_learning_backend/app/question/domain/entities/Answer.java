package com.thinhlh.mi_learning_backend.app.question.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    private UUID id;

    private String content;

    private boolean isCorrect;

    @ManyToOne
    private Question question;
}
