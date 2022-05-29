package com.thinhlh.mi_learning_backend.app.user.controller;

import com.thinhlh.mi_learning_backend.app.user.controller.dto.ChangePasswordRequest;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.UserDetailResponse;
import com.thinhlh.mi_learning_backend.app.user.domain.usecase.ChangePasswordUseCase;
import com.thinhlh.mi_learning_backend.app.user.domain.usecase.GetUserDetailUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController extends BaseController {


    private final GetUserDetailUseCase getUserDetailUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @GetMapping("/user/me")
    private ResponseEntity<BaseResponse<UserDetailResponse>> getUserDetail(HttpServletRequest request) {

        var email = ServletHelper.retrieveUsernameAndRolesFromRequest(request, () -> {

        }).getFirst();

        return successResponse(getUserDetailUseCase.invoke(email));
    }

    @PostMapping("profile/change-password")
    private ResponseEntity<BaseResponse<Boolean>> changePassword(HttpServletRequest request, @RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        var email = ServletHelper.retrieveUsernameAndRolesFromRequest(request, null).getFirst();

        changePasswordRequest.setEmail(email);
        return successResponse(changePasswordUseCase.invoke(changePasswordRequest));

    }


}
