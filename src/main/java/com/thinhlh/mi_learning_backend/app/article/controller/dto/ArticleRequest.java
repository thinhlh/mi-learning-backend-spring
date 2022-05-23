package com.thinhlh.mi_learning_backend.app.article.controller.dto;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleRequest {
    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    @URL
    private String thumbnail;

    @NotBlank
    @URL
    private String url;
}
