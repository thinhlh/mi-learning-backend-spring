package com.thinhlh.mi_learning_backend.app.comment.domain.service;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<Comment> getComments(UUID lessonId);

}
