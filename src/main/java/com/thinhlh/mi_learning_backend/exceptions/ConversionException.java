package com.thinhlh.mi_learning_backend.exceptions;

import com.thinhlh.mi_learning_backend.base.BaseException;
import lombok.NonNull;

import java.util.Optional;

public class ConversionException extends BaseException {
    public ConversionException(String message) {
        super(message);
    }
}
