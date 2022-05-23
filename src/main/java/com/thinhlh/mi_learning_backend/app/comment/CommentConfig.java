package com.thinhlh.mi_learning_backend.app.comment;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Comment;
import com.thinhlh.mi_learning_backend.app.comment.data.repository.CommentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.UUID;

@Configuration
public class CommentConfig {

//    @Bean
    int configComment(CommentRepository commentRepository) {
        var parentComment1 =
                Comment.builder()
                        .id(UUID.randomUUID())
                        .content("Parent Comment 1")
                        .updatedTime(LocalDateTime.now())
                        .build();

        var parentComment2 = Comment.builder()
                .id(UUID.randomUUID())
                .content("Parent Comment 1")
                .updatedTime(LocalDateTime.now())
                .build();

        var childComment1Of1 =
                Comment.builder()
                        .id(UUID.randomUUID())
                        .content("Child Comment 1 of 1")
                        .updatedTime(LocalDateTime.now())
                        .build();

        var childComment2Of1 =
                Comment.builder()
                        .id(UUID.randomUUID())
                        .content("Child Comment 2 of 1")
                        .updatedTime(LocalDateTime.now())
                        .build();


        parentComment1.setComments(new LinkedHashSet<>() {{
            add(childComment1Of1);
            add(childComment2Of1);
        }});

        commentRepository.saveAll(
                new ArrayList<>() {
                    {
                        add(childComment1Of1);
                        add(childComment2Of1);
                        add(parentComment1);
                        add(parentComment2);
                    }
                }
        );


        return 0;
    }
}
