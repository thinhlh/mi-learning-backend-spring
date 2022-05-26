package com.thinhlh.mi_learning_backend.app.category.controller.dto;

import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface CategoryMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "courses", ignore = true)
    Category toCategory(CategoryRequest request);

}
