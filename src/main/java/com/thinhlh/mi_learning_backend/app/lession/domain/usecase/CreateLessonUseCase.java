package com.thinhlh.mi_learning_backend.app.lession.domain.usecase;


import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonMapper;
import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonRequest;
import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonResponse;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Component
public class CreateLessonUseCase implements BaseUseCase {

    private final LessonService service;
    private final LessonMapper mapper;

    @Override
    public LessonResponse invoke(Object data) throws RuntimeException {
        return mapper.toResponse(service.createLesson((LessonRequest) data));
    }
}
