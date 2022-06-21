package com.thinhlh.mi_learning_backend.app.user.data.service;

import com.thinhlh.mi_learning_backend.app.auth.controller.dto.AuthMapper;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.role.data.repository.RoleRepository;
import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.ScheduleRequest;
import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.ScheduleColor;
import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.ScheduleStatus;
import com.thinhlh.mi_learning_backend.app.schedule.domain.service.ScheduleService;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import com.thinhlh.mi_learning_backend.app.teacher.data.repository.TeacherRepository;
import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.ChangePasswordRequest;
import com.thinhlh.mi_learning_backend.app.user.controller.dto.UpdateUserProfileRequest;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

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

    private final ScheduleService scheduleService;
    private final AuthMapper authMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


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
    public com.thinhlh.mi_learning_backend.app.user.domain.entity.User createUser(RegisterRequest request) {
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

        user = userRepository.save(user);

        initSchedules(user.getEmail());

        return user;
    }


    private void initSchedules(String email) {
        // TODAY
        scheduleService.createSchedule(
                ScheduleRequest
                        .builder()
                        .email(email)
                        .color(ScheduleColor.values()[new Random().nextInt(0, 7)].name())
                        .dueDate(LocalDateTime.now())
                        .location("Microsoft Teams - msjkl")
                        .title("Taking an online test at Microsoft Teams")
                        .status(ScheduleStatus.values()[new Random().nextInt(0, 3)].name())
                        .note("This is a note. Remember to bring your own calculator. We don 't have any responsibility to resolve your problem during the test.")
                        .build());

        ScheduleRequest
                .builder()
                .email(email)
                .color(ScheduleColor.values()[new Random().nextInt(0, 7)].name())
                .dueDate(LocalDateTime.now())
                .location("Online")
                .title("Double check the mark")
                .status(ScheduleStatus.values()[new Random().nextInt(0, 3)].name())
                .note("There is no exception when forget this event.")
                .build();

        ScheduleRequest
                .builder()
                .email(email)
                .color(ScheduleColor.values()[new Random().nextInt(0, 7)].name())
                .dueDate(LocalDateTime.now())
                .location("Home")
                .title("Taking your E-Learning exercise")
                .status(ScheduleStatus.values()[new Random().nextInt(0, 3)].name())
                .note("This is a note. Remember to bring your own calculator. We don 't have any responsibility to resolve your problem during the test.")
                .build();

        scheduleService.createSchedule(
                ScheduleRequest
                        .builder()
                        .email(email)
                        .color(ScheduleColor.values()[new Random().nextInt(0, 7)].name())
                        .dueDate(
                                LocalDateTime.of(
                                        2022,
                                        new Random().nextInt(4, 7),
                                        new Random().nextInt(1, 30),
                                        new Random().nextInt(1, 23),
                                        new Random().nextInt(0, 60)
                                )
                        )
                        .location("Home")
                        .title("Finish 4 exercises")
                        .status(ScheduleStatus.values()[new Random().nextInt(0, 3)].name())
                        .note("Do all your homework before due date.")
                        .build());

        scheduleService.createSchedule(
                ScheduleRequest
                        .builder()
                        .email(email)
                        .color(ScheduleColor.values()[new Random().nextInt(0, 7)].name())
                        .dueDate(
                                LocalDateTime.of(
                                        2022,
                                        new Random().nextInt(4, 7),
                                        new Random().nextInt(1, 30),
                                        new Random().nextInt(1, 23),
                                        new Random().nextInt(0, 60)
                                )
                        )
                        .location("School")
                        .title("Go to school please.")
                        .status(ScheduleStatus.values()[new Random().nextInt(0, 3)].name())
                        .note("Also bring your note book.")
                        .build());
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

    @Override
    @Transactional
    public boolean changePassword(ChangePasswordRequest request) {
        var user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            var currentHashedPassword = user.get().getPassword();

            if (passwordEncoder.matches(request.getCurrentPassword(), currentHashedPassword)) {
                var hashedNewPassword = passwordEncoder.encode(request.getNewPassword());
                user.get().setPassword(hashedNewPassword);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NotFoundException(USER_NOT_FOUND);
        }

    }

    @Override
    @Transactional
    public UserDetailResponse updateUserProfile(UpdateUserProfileRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            throw new NotFoundException(USER_NOT_FOUND);
        } else {
            try {
                if (request.getName() != null) user.setName(request.getName());
                if (request.getAvatar() != null) user.setAvatar(request.getAvatar());
                if (request.getOccupation() != null) user.setOccupation(request.getOccupation());
                if (request.getBirthday() != null)
                    user.setBirthday(LocalDate.from(LocalDateTime.parse(request.getBirthday(), DateTimeFormatter.ISO_LOCAL_DATE)));
            } catch (Exception ignored) {

            }
        }
        return userMapper.toUserDetailResponse(user);
    }
}
