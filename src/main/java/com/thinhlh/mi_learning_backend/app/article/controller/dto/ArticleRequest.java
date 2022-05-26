package com.thinhlh.mi_learning_backend.app.article.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Past
    private LocalDate createdDate;
}
