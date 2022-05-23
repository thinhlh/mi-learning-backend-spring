package com.thinhlh.mi_learning_backend.app.section.controller;

import com.thinhlh.mi_learning_backend.app.section.controller.dto.SectionRequest;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.section.domain.usecase.CreateSectionUseCase;
import com.thinhlh.mi_learning_backend.app.section.domain.usecase.GetSectionsUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SectionController extends BaseController {

    private final GetSectionsUseCase getSectionsUseCase;
    private final CreateSectionUseCase createSectionUseCase;

    @GetMapping("/sections")
    private ResponseEntity<BaseResponse<List<Section>>> getAllSections(@RequestParam @NotBlank UUID courseId) {
        return successResponse(getSectionsUseCase.invoke(courseId));
    }

    @PostMapping("/section")
    private ResponseEntity<BaseResponse<Section>> createSection(@RequestBody @Valid SectionRequest request) {
        return successResponse(createSectionUseCase.invoke(request));
    }

}
