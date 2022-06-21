package com.thinhlh.mi_learning_backend.app.category.data.service;

import com.thinhlh.mi_learning_backend.app.category.controller.dto.CategoryMapper;
import com.thinhlh.mi_learning_backend.app.category.controller.dto.CategoryRequest;
import com.thinhlh.mi_learning_backend.app.category.data.repository.CategoryRepository;
import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.category.domain.service.CategoryService;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public List<Category> getAllCategories() {
        return ListHelper.toList(categoryRepository.findAll());
    }

    @Override
    public Category createCategory(CategoryRequest request) {
        var category = mapper.toCategory(request);

        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
