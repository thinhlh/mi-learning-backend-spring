package com.thinhlh.mi_learning_backend.app.role.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "title", nullable = false, updatable = false, unique = true)
    private String title;

    public enum RoleName {

        student("student"),

        teacher("teacher"),

        admin("admin");

        RoleName(String value) {
        }
    }
}