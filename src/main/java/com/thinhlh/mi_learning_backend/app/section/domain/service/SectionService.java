package com.thinhlh.mi_learning_backend.app.section.domain.service;

import com.thinhlh.mi_learning_backend.app.section.controller.dto.SectionRequest;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;

import java.util.List;
import java.util.UUID;

public interface SectionService {
    List<Section> getAllSections(UUID courseId);

    Section createSection(SectionRequest request);
}
