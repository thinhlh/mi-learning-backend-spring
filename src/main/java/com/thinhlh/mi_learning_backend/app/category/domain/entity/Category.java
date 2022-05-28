package com.thinhlh.mi_learning_backend.app.category.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Category {
    @Id
    private UUID id;

    @Column(unique = true)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Course> courses = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Article> articles = new LinkedHashSet<>();
}
