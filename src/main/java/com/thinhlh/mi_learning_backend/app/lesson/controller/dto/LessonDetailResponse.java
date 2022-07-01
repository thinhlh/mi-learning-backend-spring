package com.thinhlh.mi_learning_backend.app.lesson.controller.dto;

import com.thinhlh.mi_learning_backend.app.lesson.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.note.domain.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonDetailResponse {
    private Lesson lesson;
    private UUID courseId;
    private UUID sectionId;
    private boolean finished;
    private List<Note> notes;
    private int playback;
}
