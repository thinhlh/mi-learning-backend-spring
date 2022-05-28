package com.thinhlh.mi_learning_backend.app.article.controller.dto;

import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ArticleResponse {
    private UUID id;
    private String author;
    private String title;
    private String thumbnail;
    private LocalDate createdDate;
    private String url;
    private String category;
}
