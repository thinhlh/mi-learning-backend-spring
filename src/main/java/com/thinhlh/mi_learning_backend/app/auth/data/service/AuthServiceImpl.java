package com.thinhlh.mi_learning_backend.app.auth.data.service;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.AuthMapper;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.LoginRequest;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.entity.Tokens;
import com.thinhlh.mi_learning_backend.app.auth.domain.service.AuthService;
import com.thinhlh.mi_learning_backend.app.role.data.repository.RoleRepository;
import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.app.user.data.repository.UserRepository;
import com.thinhlh.mi_learning_backend.app.user.domain.entity.User;
import com.thinhlh.mi_learning_backend.app.user.domain.service.UserService;
import com.thinhlh.mi_learning_backend.exceptions.CustomAuthenticationException;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.exceptions.ObjectAlreadyExistsException;
import com.thinhlh.mi_learning_backend.helper.SecurityHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userService.createUser(request);
    }

    @Override
    public Tokens loginUser(LoginRequest request) {
        var user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            var role = user.get().getRole();
            return new Tokens(
                    SecurityHelper
                            .generateToken(
                                    user.get().getEmail(),
                                    List.of(role.getTitle()),
                                    true
                            ),
                    SecurityHelper
                            .generateToken(
                                    user.get().getEmail(),
                                    List.of(user.get().getRole().getTitle()),
                                    false
                            )
            );
        } else {
            throw new CustomAuthenticationException("Invalid username or password");
        }
    }
}
