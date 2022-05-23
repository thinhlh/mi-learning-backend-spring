package com.thinhlh.mi_learning_backend.exceptions;

import com.thinhlh.mi_learning_backend.base.BaseException;

public class ObjectAlreadyExistsException extends BaseException {
    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
