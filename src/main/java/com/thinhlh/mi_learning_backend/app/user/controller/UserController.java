package com.thinhlh.mi_learning_backend.app.user.controller;

import com.thinhlh.mi_learning_backend.app.user.controller.dto.UserDetailResponse;
import com.thinhlh.mi_learning_backend.app.user.domain.usecase.GetUserDetailUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserController extends BaseController {


    private final GetUserDetailUseCase getUserDetailUseCase;

    @GetMapping("/user")
    private ResponseEntity<BaseResponse<UserDetailResponse>> getUserDetail(HttpServletRequest request) {

        var email = ServletHelper.retrieveUsernameAndRolesFromRequest(request, () -> {

        }).getFirst();

        return successResponse(getUserDetailUseCase.invoke(email));
    }
}
