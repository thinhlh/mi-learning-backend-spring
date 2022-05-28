package com.thinhlh.mi_learning_backend.exceptions;

import com.thinhlh.mi_learning_backend.base.BaseException;

public class NotFoundException extends BaseException {

    static final String NOT_FOUND_MESSAGE = "Object not found";

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super(NOT_FOUND_MESSAGE);
    }

}
