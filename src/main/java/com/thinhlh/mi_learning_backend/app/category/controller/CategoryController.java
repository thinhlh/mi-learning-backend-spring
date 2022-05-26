package com.thinhlh.mi_learning_backend.app.category.controller;

import com.thinhlh.mi_learning_backend.app.category.controller.dto.CategoryRequest;
import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import com.thinhlh.mi_learning_backend.app.category.domain.usecase.CreateCategoryUseCase;
import com.thinhlh.mi_learning_backend.app.category.domain.usecase.GetCategoriesUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final GetCategoriesUseCase getCategoriesUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;


    @GetMapping("/categories")
    private ResponseEntity<BaseResponse<List<Category>>> getCategories() {
        return successResponse(getCategoriesUseCase.invoke(null));
    }

    @PostMapping("/category")
    private ResponseEntity<BaseResponse<Category>> createCategory(@RequestBody CategoryRequest request) {
        return successResponse(createCategoryUseCase.invoke(request));
    }

}
