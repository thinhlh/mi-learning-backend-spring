package com.thinhlh.mi_learning_backend.exceptions;

import com.thinhlh.mi_learning_backend.base.BaseResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * Current exceptions that are handled:
 *
 * @exception CustomAuthenticationException
 * @exception MethodArgumentNotValidException
 * @exception NotFoundException
 * @exception ObjectAlreadyExistsException
 * @exception TypeMismatchException
 * @exception RuntimeException
 */

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<BaseResponse<String>> handleAuthenticationException(
            CustomAuthenticationException exception,
            WebRequest request
    ) {
        var errorResponse = BaseResponse.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {
        var builder = new StringBuilder();

        ex.getFieldErrors().forEach(error -> {
            builder.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
        });

        return new ResponseEntity<>(BaseResponse.error(builder.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse<String>> handleNotFoundException(
            NotFoundException exception,
            WebRequest request
    ) {
        var errorResponse = BaseResponse.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<BaseResponse<String>> handleNotFoundException(
            ObjectAlreadyExistsException exception,
            WebRequest request
    ) {
        var errorResponse = BaseResponse.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request
    ) {
        var errorResponse = BaseResponse.error(ex.getMessage());

        logger.error(errorResponse);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = {UnknownException.class, RuntimeException.class})
    public ResponseEntity<BaseResponse<String>> handleAllException(
            RuntimeException exception,
            WebRequest request
    ) {
        var errorResponse = BaseResponse.error(exception.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
