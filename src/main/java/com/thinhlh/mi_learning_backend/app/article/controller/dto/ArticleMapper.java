package com.thinhlh.mi_learning_backend.app.article.controller.dto;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, UUID.class})
public interface ArticleMapper {
    @Mapping(target = "category", source = "article.category.title")
    ArticleResponse toArticleResponse(Article article);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "category", ignore = true)
    Article toArticle(ArticleRequest articleRequest);

}
