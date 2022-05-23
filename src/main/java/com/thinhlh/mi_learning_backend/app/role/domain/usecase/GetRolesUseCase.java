package com.thinhlh.mi_learning_backend.app.role.domain.usecase;

import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.app.role.domain.service.RoleService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import com.thinhlh.mi_learning_backend.exceptions.ConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRolesUseCase implements BaseUseCase {

    private final RoleService service;

    @Override
    public List<Role> invoke(Object data) throws ConversionException {
        return service.getRoles();
    }
}
