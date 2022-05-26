package com.thinhlh.mi_learning_backend.app.category.domain.service;

import com.thinhlh.mi_learning_backend.app.category.controller.dto.CategoryRequest;
import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();

    Category createCategory(CategoryRequest request);

    Category getCategoryByTitle(String title);

}
