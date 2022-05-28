package com.thinhlh.mi_learning_backend.app.auth.domain.usecase;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.LoginRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.entity.Tokens;
import com.thinhlh.mi_learning_backend.app.auth.domain.service.AuthService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import com.thinhlh.mi_learning_backend.exceptions.ConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUseCase implements BaseUseCase<LoginRequest,Tokens> {

    private final AuthService authService;

    @Override
    public Tokens invoke(LoginRequest data) throws RuntimeException {
        return authService.loginUser(data);
    }
}
