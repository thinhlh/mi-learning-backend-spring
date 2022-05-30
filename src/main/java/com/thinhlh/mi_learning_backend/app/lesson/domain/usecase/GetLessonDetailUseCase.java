package com.thinhlh.mi_learning_backend.app.lesson.domain.usecase;

import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonDetailRequest;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonDetailResponse;
import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetLessonDetailUseCase implements BaseUseCase<LessonDetailRequest, LessonDetailResponse> {

    private final LessonService lessonService;

    @Override
    public LessonDetailResponse invoke(LessonDetailRequest data) throws RuntimeException {
        return lessonService.getLessonDetail(data);
    }
}
