package com.thinhlh.mi_learning_backend.app.article.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Article {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "url", nullable = false)
    private String url;
}