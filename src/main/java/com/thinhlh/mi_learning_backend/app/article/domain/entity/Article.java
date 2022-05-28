package com.thinhlh.mi_learning_backend.app.article.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
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

    @Column(name = "created_date", nullable = false, columnDefinition = "date default CURRENT_DATE")
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}