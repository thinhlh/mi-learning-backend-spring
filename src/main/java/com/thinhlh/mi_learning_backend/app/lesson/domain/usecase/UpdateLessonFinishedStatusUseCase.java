package com.thinhlh.mi_learning_backend.app.lesson.domain.usecase;

import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.UpdateLessonFinishedStatusRequest;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateLessonFinishedStatusUseCase implements BaseUseCase<UpdateLessonFinishedStatusRequest, Boolean> {

    private final LessonService lessonService;

    @Override
    public Boolean invoke(UpdateLessonFinishedStatusRequest data) throws RuntimeException {
        return lessonService.updateLessonFinishedStatus(data);
    }
}
