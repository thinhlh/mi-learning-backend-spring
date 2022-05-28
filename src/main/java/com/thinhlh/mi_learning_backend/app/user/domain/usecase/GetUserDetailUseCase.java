package com.thinhlh.mi_learning_backend.app.user.domain.usecase;


import com.thinhlh.mi_learning_backend.app.user.controller.dto.UserDetailResponse;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.UserMapper;
import com.thinhlh.mi_learning_backend.app.user.domain.service.UserService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserDetailUseCase implements BaseUseCase<String,UserDetailResponse> {

    private final UserService userService;

    @Override
    public UserDetailResponse invoke(String data) throws RuntimeException {
        return userService.getUserDetail(data);
    }
}
