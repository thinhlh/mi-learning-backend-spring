package com.thinhlh.mi_learning_backend.app.article.controller;

import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleMapper;
import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleRequest;
import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleResponse;
import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import com.thinhlh.mi_learning_backend.app.article.domain.usecase.CreateArticleUseCase;
import com.thinhlh.mi_learning_backend.app.article.domain.usecase.GetArticlesUseCase;
import com.thinhlh.mi_learning_backend.app.test.TestRepository;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleController extends BaseController {

    private final ArticleMapper mapper;
    private final GetArticlesUseCase getArticlesUseCase;
    private final CreateArticleUseCase createArticleUseCase;
    private final TestRepository repository;

    @GetMapping("/articles")
    private ResponseEntity<BaseResponse<List<ArticleResponse>>> getArticles() {

        var articles = getArticlesUseCase.invoke(null);

        return successResponse(ListHelper.mapTo(articles, mapper::toArticleResponse));
    }

    @PostMapping("/article")
    private ResponseEntity<BaseResponse<Article>> createArticle(@RequestBody @Valid ArticleRequest request) {
        return successResponse(createArticleUseCase.invoke(request));
    }
}
