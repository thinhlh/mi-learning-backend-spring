package com.thinhlh.mi_learning_backend.app.auth.domain.usecase;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.service.AuthService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import com.thinhlh.mi_learning_backend.exceptions.ConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUseCase implements BaseUseCase {

    private final AuthService authService;

    @Override
    public Object invoke(Object data) throws ConversionException {
        authService.registerUser((RegisterRequest) data);
        return null;
    }
}
