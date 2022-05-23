package com.thinhlh.mi_learning_backend.app.article;

import com.thinhlh.mi_learning_backend.app.article.data.repository.ArticleRepository;
import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Configuration
public class ArticleConfig {
    @Bean
    CommandLineRunner commandLineRunner(ArticleRepository repository) {
        return args -> {
//            repository.save(
//                    new Article(
//                            UUID.randomUUID(),
//                            "Author 1",
//                            "Title 1",
//                            "Thumbnail 1",
//                            LocalDateTime.now(),
//                            "URL 1"
//                    )
//            );
        };

    }
}
