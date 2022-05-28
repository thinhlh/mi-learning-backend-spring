package com.thinhlh.mi_learning_backend.app.article.data.service;

import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleMapper;
import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleRequest;
import com.thinhlh.mi_learning_backend.app.article.data.repository.ArticleRepository;
import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import com.thinhlh.mi_learning_backend.app.article.domain.service.ArticleService;
import com.thinhlh.mi_learning_backend.app.category.data.repository.CategoryRepository;
import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.category.domain.service.CategoryService;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleMapper mapper;
    private final CategoryService categoryService;

    @Override
    public List<Article> getArticles() {
        return ListHelper.toList(articleRepository.findAll());
    }

    @Override
    public Article createArticle(ArticleRequest request) {
        var article = mapper.toArticle(request);
        var category = categoryService.getCategoryByTitle(request.getCategory());
        article.setCategory(category);

        return articleRepository.save(article);
    }
}
