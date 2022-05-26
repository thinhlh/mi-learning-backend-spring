package com.thinhlh.mi_learning_backend.app.user.data.service;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.AuthMapper;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.role.data.repository.RoleRepository;
import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import com.thinhlh.mi_learning_backend.app.teacher.data.repository.TeacherRepository;
import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.UserDetailResponse;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.UserMapper;
import com.thinhlh.mi_learning_backend.app.user.data.repository.UserRepository;
import com.thinhlh.mi_learning_backend.app.user.domain.service.UserService;
import com.thinhlh.mi_learning_backend.exceptions.NotFoundException;
import com.thinhlh.mi_learning_backend.exceptions.ObjectAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final String USER_NOT_FOUND = "User not found";
    private final String USER_ALREADY_REGISTERED = "User already registered";
    private final String ROLE_NOT_FOUND = "Role not found";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final AuthMapper authMapper;
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByEmail(username);

        if (user.isEmpty())
            throw new NotFoundException("User not found");

        var roles = user.get().getRole();

        var authorities = new ArrayList<SimpleGrantedAuthority>() {{
            add(new SimpleGrantedAuthority(roles.getTitle()));
        }};

        return new User(username, user.get().getPassword(), authorities);
    }

    @Override
    public void createUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new ObjectAlreadyExistsException(USER_ALREADY_REGISTERED);

        var role = roleRepository.getRoleByTitle(request.getRole());

        if (role == null) throw new NotFoundException(ROLE_NOT_FOUND);

        var user = authMapper.toUser(request);

        user.setRole(role);

        if (role.getTitle().equals(Role.RoleName.student.name())) {
            // Student
            Student student = new Student();
            student.setId(user.getId());
            student.setUser(user);

            user.setStudent(student);
            studentRepository.save(student);

        } else if (role.getTitle().equals(Role.RoleName.teacher.name())) {
            // Teacher
            Teacher teacher = new Teacher();
            teacher.setId(user.getId());
            teacher.setUser(user);

            user.setTeacher(teacher);
            teacherRepository.save(teacher);

        } else if (role.getTitle().equals(Role.RoleName.admin.name())) {
            // Admin
        }

        userRepository.save(user);
    }

    @Override
    public UserDetailResponse getUserDetail(String email) {
        var user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return userMapper.toUserDetailResponse(user.get());
        } else {
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }
}
