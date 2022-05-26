package com.thinhlh.mi_learning_backend.app.category.domain.usecase;

import com.thinhlh.mi_learning_backend.app.category.controller.dto.CategoryRequest;
import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.category.domain.service.CategoryService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategoryUseCase implements BaseUseCase {

    private final CategoryService categoryService;

    @Override
    public Category invoke(Object data) throws RuntimeException {
        return categoryService.createCategory((CategoryRequest) data
        );
    }
}
