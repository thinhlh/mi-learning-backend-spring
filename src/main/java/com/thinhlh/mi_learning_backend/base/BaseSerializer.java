package com.thinhlh.mi_learning_backend.base;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.lang.Nullable;

import java.util.Optional;

public abstract class BaseSerializer<T> extends JsonSerializer<T> {
    @Nullable
    protected final JsonSerializer<T> defaultSerializer = null;
}
