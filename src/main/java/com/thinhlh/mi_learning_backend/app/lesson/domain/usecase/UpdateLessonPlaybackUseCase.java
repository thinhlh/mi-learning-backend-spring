package com.thinhlh.mi_learning_backend.app.lesson.domain.usecase;

import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.UpdateLessonPlaybackRequest;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateLessonPlaybackUseCase implements BaseUseCase<UpdateLessonPlaybackRequest, Boolean> {

    private final LessonService lessonService;

    @Override
    public Boolean invoke(UpdateLessonPlaybackRequest data) throws RuntimeException {
        return lessonService.updateLessonPlayback(data);
    }
}
