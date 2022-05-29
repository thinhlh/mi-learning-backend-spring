package com.thinhlh.mi_learning_backend.app.user.domain.usecase;

import com.thinhlh.mi_learning_backend.app.user.controller.dto.ChangePasswordRequest;
import com.thinhlh.mi_learning_backend.app.user.domain.service.UserService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangePasswordUseCase implements BaseUseCase<ChangePasswordRequest, Boolean> {
    private final UserService userService;

    @Override
    public Boolean invoke(ChangePasswordRequest changePasswordRequest) throws RuntimeException {
        return userService.changePassword(changePasswordRequest);
    }
}
