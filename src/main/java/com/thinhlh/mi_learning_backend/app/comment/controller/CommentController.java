package com.thinhlh.mi_learning_backend.app.comment.controller;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Comment;
import com.thinhlh.mi_learning_backend.app.comment.domain.usecase.GetCommentsUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentController extends BaseController {

    private final GetCommentsUseCase getCommentsUseCase;

    @GetMapping("/comments/{lessonId}")
    public ResponseEntity<BaseResponse<List<Comment>>> getComments(@PathVariable() UUID lessonId) {
        return successResponse(getCommentsUseCase.invoke(lessonId));
    }

}
