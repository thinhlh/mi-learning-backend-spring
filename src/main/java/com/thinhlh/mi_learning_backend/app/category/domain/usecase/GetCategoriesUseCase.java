package com.thinhlh.mi_learning_backend.app.category.domain.usecase;

import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.category.domain.service.CategoryService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCategoriesUseCase implements BaseUseCase<Object, List<Category>> {

    private final CategoryService categoryService;

    @Override
    public List<Category> invoke(Object data) throws RuntimeException {
        return categoryService.getAllCategories();
    }
}
