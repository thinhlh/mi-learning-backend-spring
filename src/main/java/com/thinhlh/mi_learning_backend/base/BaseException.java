package com.thinhlh.mi_learning_backend.base;

import java.util.Optional;

public abstract class BaseException extends RuntimeException {
    protected BaseException(String message) {
        super(message);
    }
}
