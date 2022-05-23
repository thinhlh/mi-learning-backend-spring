package com.thinhlh.mi_learning_backend.app.test;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {
    TestResponse toTestResponse(Test test);
}
