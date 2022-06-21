package com.thinhlh.mi_learning_backend.app.lesson.controller;

import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.*;
import com.thinhlh.mi_learning_backend.app.lesson.domain.usecase.CreateLessonUseCase;
import com.thinhlh.mi_learning_backend.app.lesson.domain.usecase.CreateOrUpdateNoteUseCase;
import com.thinhlh.mi_learning_backend.app.lesson.domain.usecase.GetLessonDetailUseCase;
import com.thinhlh.mi_learning_backend.app.lesson.domain.usecase.GetLessonsUseCase;
import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class LessonController extends BaseController {

    private final GetLessonsUseCase getLessonsUseCase;
    private final CreateLessonUseCase createLessonUseCase;
    private final GetLessonDetailUseCase getLessonDetailUseCase;
    private final CreateOrUpdateNoteUseCase createOrUpdateNoteUseCase;

    @GetMapping("/lessons")
    private ResponseEntity<BaseResponse<List<LessonResponse>>> getLessonsBySection(@RequestParam UUID sectionId) {
        return successResponse(getLessonsUseCase.invoke(sectionId));
    }

    @PostMapping("/lesson")
    private ResponseEntity<BaseResponse<LessonResponse>> createLesson(@RequestBody @Valid LessonRequest request) {
        return successResponse(createLessonUseCase.invoke(request));
    }

    @GetMapping("/lesson/detail")
    private ResponseEntity<BaseResponse<LessonDetailResponse>> getLessonDetail(@RequestParam @NotNull UUID lessonId, HttpServletRequest request) {
        return successResponse(
                getLessonDetailUseCase.invoke(
                        new LessonDetailRequest(
                                ServletHelper.retrieveUsernameAndRolesFromRequest(request, null).getFirst(),
                                lessonId)
                )
        );
    }

    @PostMapping("/note")
    private ResponseEntity<BaseResponse<Note>> createOrUpdateNote(@RequestBody CreateNoteRequest createNoteRequest, HttpServletRequest request) {
        createNoteRequest.setEmail(ServletHelper.retrieveUsernameAndRolesFromRequest(request, null).getFirst());
        return successResponse(
                createOrUpdateNoteUseCase.invoke(createNoteRequest)
        );
    }

}
