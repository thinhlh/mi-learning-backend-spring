package com.thinhlh.mi_learning_backend.exceptions;

import com.thinhlh.mi_learning_backend.base.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }

}
