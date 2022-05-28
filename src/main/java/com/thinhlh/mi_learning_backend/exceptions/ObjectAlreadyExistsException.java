package com.thinhlh.mi_learning_backend.exceptions;

import com.thinhlh.mi_learning_backend.base.BaseException;

public class ObjectAlreadyExistsException extends BaseException {

    static final String OBJECT_EXISTS_MESSAGE = "Object already exists";

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

    public ObjectAlreadyExistsException(){
        super(OBJECT_EXISTS_MESSAGE);
    }
}
