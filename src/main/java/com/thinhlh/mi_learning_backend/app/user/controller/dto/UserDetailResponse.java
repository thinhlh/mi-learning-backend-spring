package com.thinhlh.mi_learning_backend.app.user.controller.dto;

import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.app.student.domain.entity.Student;
import com.thinhlh.mi_learning_backend.app.teacher.domain.entity.Teacher;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Setter
public class UserDetailResponse {
    private UUID id;

    private String name;

    private String email;

    private String occupation;

    private LocalDate birthday;

    private String avatar;

    private Boolean enabled = false;

    private String role;
}
