package com.thinhlh.mi_learning_backend.app.user.domain.service;

import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;

public interface UserService {

    User createUser(User user, String roleTitle);

}
