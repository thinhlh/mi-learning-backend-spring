package com.thinhlh.mi_learning_backend.exceptions;


import com.thinhlh.mi_learning_backend.base.BaseException;

public class UnknownException extends BaseException {
    public UnknownException(String message) {
        super(message);
    }
}
