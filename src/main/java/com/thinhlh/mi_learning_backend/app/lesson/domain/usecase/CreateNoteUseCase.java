package com.thinhlh.mi_learning_backend.app.lesson.domain.usecase;

import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.CreateNoteRequest;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;
import com.thinhlh.mi_learning_backend.app.note.domain.service.NoteService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateNoteUseCase implements BaseUseCase<CreateNoteRequest, Note> {

    private final NoteService noteService;

    @Override
    public Note invoke(CreateNoteRequest data) throws RuntimeException {
        return noteService.createNote(data);
    }
}
