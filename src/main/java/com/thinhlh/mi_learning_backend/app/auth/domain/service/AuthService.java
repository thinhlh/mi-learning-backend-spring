package com.thinhlh.mi_learning_backend.app.auth.domain.service;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.LoginRequest;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.entity.Tokens;
import com.thinhlh.mi_learning_backend.app.auth.domain.usecase.LoginUseCase;
import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;

public interface AuthService {
    User registerUser(RegisterRequest request);

    Tokens loginUser(LoginRequest request);
}
