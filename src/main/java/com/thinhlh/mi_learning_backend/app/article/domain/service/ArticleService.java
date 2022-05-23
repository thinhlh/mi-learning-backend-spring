package com.thinhlh.mi_learning_backend.app.article.domain.service;

import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleRequest;
import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getArticles();

    Article createArticle(Article article);
}
