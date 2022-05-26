package com.thinhlh.mi_learning_backend.app.teacher.domain.entity;

import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "teacher")
@Getter
@Setter
public class Teacher {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;


    @OneToOne(fetch = FetchType.LAZY, optional = false, mappedBy = "teacher")
    private User user;
}