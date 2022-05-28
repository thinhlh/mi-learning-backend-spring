package com.thinhlh.mi_learning_backend.app.section.domain.usecase;

import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.section.domain.service.SectionService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetSectionsUseCase implements BaseUseCase<UUID,List<Section>> {

    private final SectionService sectionService;

    @Override
    public List<Section> invoke(UUID data) throws RuntimeException {
        return sectionService.getAllSections(data);
    }
}
