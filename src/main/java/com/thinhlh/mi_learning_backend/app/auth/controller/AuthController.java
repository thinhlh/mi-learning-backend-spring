package com.thinhlh.mi_learning_backend.app.auth.controller;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.LoginRequest;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.entity.Tokens;
import com.thinhlh.mi_learning_backend.app.auth.domain.usecase.LoginUseCase;
import com.thinhlh.mi_learning_backend.app.auth.domain.usecase.RegisterUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    private ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequest userRegister) {
        registerUseCase.invoke(userRegister);
        return successResponse(null);
    }

//    @GetMapping("/login")
//    private ResponseEntity<BaseResponse<Tokens>> login(@RequestBody @Valid LoginRequest loginRequest) {
//        return successResponse(loginUseCase.invoke(loginRequest));
//    }
}
