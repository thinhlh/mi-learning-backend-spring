package com.thinhlh.mi_learning_backend.app.lession.domain.usecase;

import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonMapper;
import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonResponse;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetLessonsUseCase implements BaseUseCase {

    private final LessonService lessonService;
    private final LessonMapper mapper;

    @Override
    public List<LessonResponse> invoke(Object data) throws RuntimeException {
        return ListHelper.mapTo(lessonService.getAllLessonOfSection((UUID) data), mapper::toResponse);
    }
}
