package com.thinhlh.mi_learning_backend.app.note.data.repository;

import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NoteRepository extends CrudRepository<Note, UUID> {
}
