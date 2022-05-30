package com.thinhlh.mi_learning_backend.app.lesson.domain.usecase;


import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonMapper;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonRequest;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonResponse;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateLessonUseCase implements BaseUseCase<LessonRequest, LessonResponse> {

    private final LessonService service;
    private final LessonMapper mapper;

    @Override
    public LessonResponse invoke(LessonRequest data) throws RuntimeException {
        return mapper.toResponse(service.createLesson(data));
    }
}
