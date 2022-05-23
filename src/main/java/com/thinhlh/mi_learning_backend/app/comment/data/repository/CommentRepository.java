package com.thinhlh.mi_learning_backend.app.comment.data.repository;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {

}
