package com.thinhlh.mi_learning_backend.app.comment.data.service;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Comment;
import com.thinhlh.mi_learning_backend.app.comment.data.repository.CommentRepository;
import com.thinhlh.mi_learning_backend.app.comment.domain.service.CommentService;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public List<Comment> getComments(UUID lessonId) {
        return ListHelper.toList(repository.findAll());
    }
}
