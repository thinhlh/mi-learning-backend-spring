package com.thinhlh.mi_learning_backend.app.user.domain.service;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.ChangePasswordRequest;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.UserDetailResponse;
import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;

public interface UserService {

    void createUser(RegisterRequest request);

    UserDetailResponse getUserDetail(String email);

    boolean changePassword(ChangePasswordRequest request);

}
