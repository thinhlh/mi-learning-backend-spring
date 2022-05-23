package com.thinhlh.mi_learning_backend.app.article.domain.usecase;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import com.thinhlh.mi_learning_backend.app.article.domain.service.ArticleService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetArticlesUseCase implements BaseUseCase {

    private final ArticleService service;

    @Override
    public List<Article> invoke(Object data) {
        return service.getArticles();
    }
}
