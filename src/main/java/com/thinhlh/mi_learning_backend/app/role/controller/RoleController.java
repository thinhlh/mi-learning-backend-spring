package com.thinhlh.mi_learning_backend.app.role.controller;

import com.thinhlh.mi_learning_backend.app.role.controller.dto.RoleMapper;
import com.thinhlh.mi_learning_backend.app.role.controller.dto.RoleResponse;
import com.thinhlh.mi_learning_backend.app.role.domain.usecase.GetRolesUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RoleController extends BaseController {

    private final RoleMapper mapper;
    private final GetRolesUseCase getRolesUseCase;

    private ResponseEntity<BaseResponse<List<RoleResponse>>> getRoles() {
        var responses = ListHelper.mapTo(getRolesUseCase.invoke(null), mapper::toResponse);
        return successResponse(responses);
    }
}
