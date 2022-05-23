package com.thinhlh.mi_learning_backend.app.article.data.repository;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends CrudRepository<Article, UUID> {

}
