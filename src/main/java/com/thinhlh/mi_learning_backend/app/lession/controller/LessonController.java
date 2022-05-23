package com.thinhlh.mi_learning_backend.app.lession.controller;

import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonResponse;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.usecase.GetLessonsUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class LessonController extends BaseController {

    private final GetLessonsUseCase getLessonsUseCase;

    @GetMapping("/lessons")
    private ResponseEntity<BaseResponse<List<LessonResponse>>> getLessonsBySection(@RequestParam UUID sectionId) {
        return successResponse(getLessonsUseCase.invoke(sectionId));
    }

}
