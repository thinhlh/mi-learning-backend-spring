package com.thinhlh.mi_learning_backend.exceptions;

import com.thinhlh.mi_learning_backend.base.BaseException;

public class CustomAuthenticationException extends BaseException {
    public CustomAuthenticationException(String message) {
        super(message);
    }
}
