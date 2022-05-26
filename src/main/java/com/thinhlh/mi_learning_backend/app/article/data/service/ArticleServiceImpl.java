package com.thinhlh.mi_learning_backend.app.article.data.service;

import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleMapper;
import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleRequest;
import com.thinhlh.mi_learning_backend.app.article.data.repository.ArticleRepository;
import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import com.thinhlh.mi_learning_backend.app.article.domain.service.ArticleService;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;
    private final ArticleMapper mapper;

    @Override
    public List<Article> getArticles() {
        return ListHelper.toList(repository.findAll());
    }

    @Override
    public Article createArticle(ArticleRequest request) {
        return repository.save(mapper.toArticle(request));
    }
}
