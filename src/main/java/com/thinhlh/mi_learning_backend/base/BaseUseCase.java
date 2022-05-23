package com.thinhlh.mi_learning_backend.base;

import com.thinhlh.mi_learning_backend.app.article.domain.entity.Comment;
import com.thinhlh.mi_learning_backend.exceptions.ConversionException;
import org.hibernate.mapping.Any;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseUseCase {
    Object invoke(Object data) throws RuntimeException;
}
