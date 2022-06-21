package com.thinhlh.mi_learning_backend.app.note.domain.service;

import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.CreateNoteRequest;
import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;

public interface NoteService {
    Note createOrUpdateNote(CreateNoteRequest request);
}
