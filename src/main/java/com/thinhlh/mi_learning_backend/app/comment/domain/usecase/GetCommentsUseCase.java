package com.thinhlh.mi_learning_backend.app.comment.domain.usecase;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Comment;
import com.thinhlh.mi_learning_backend.app.comment.data.repository.CommentRepository;
import com.thinhlh.mi_learning_backend.app.comment.domain.service.CommentService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetCommentsUseCase implements BaseUseCase<UUID, List<Comment>> {

    private final CommentService service;

    @Override
    public List<Comment> invoke(UUID data) throws RuntimeException {
        return service.getComments(data);
    }
}
