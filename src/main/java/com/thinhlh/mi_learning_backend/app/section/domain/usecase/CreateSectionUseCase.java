package com.thinhlh.mi_learning_backend.app.section.domain.usecase;

import com.thinhlh.mi_learning_backend.app.section.controller.dto.SectionRequest;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.section.domain.service.SectionService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateSectionUseCase implements BaseUseCase {

    private final SectionService sectionService;

    @Override
    public Section invoke(Object data) throws RuntimeException {

        return sectionService.createSection((SectionRequest) data);
    }
}
