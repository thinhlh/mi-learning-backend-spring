package com.thinhlh.mi_learning_backend.app.lession.controller;

import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonRequest;
import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonResponse;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.usecase.CreateLessonUseCase;
import com.thinhlh.mi_learning_backend.app.lession.domain.usecase.GetLessonsUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class LessonController extends BaseController {

    private final GetLessonsUseCase getLessonsUseCase;
    private final CreateLessonUseCase createLessonUseCase;

    @GetMapping("/lessons")
    private ResponseEntity<BaseResponse<List<LessonResponse>>> getLessonsBySection(@RequestParam UUID sectionId) {
        return successResponse(getLessonsUseCase.invoke(sectionId));
    }

    @PostMapping("/lesson")
    private ResponseEntity<BaseResponse<LessonResponse>> createLesson(@RequestBody @Valid LessonRequest request) {
        return successResponse(createLessonUseCase.invoke(request));
    }

}
