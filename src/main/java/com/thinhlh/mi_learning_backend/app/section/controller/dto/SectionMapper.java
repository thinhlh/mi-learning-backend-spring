package com.thinhlh.mi_learning_backend.app.section.controller.dto;

import com.thinhlh.mi_learning_backend.app.lession.controller.dto.LessonMapper;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface SectionMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Section toSection(SectionRequest request);
}
