package com.thinhlh.mi_learning_backend.base;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected <T> ResponseEntity<BaseResponse<T>> successResponse(T data) {
        return ResponseEntity.ok(BaseResponse.success(data));
    }
}
