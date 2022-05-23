package com.thinhlh.mi_learning_backend.app.test;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Test {
    @Id
    private Integer id;
    private String title;
}
