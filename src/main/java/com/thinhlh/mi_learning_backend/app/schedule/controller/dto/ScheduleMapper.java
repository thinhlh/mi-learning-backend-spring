package com.thinhlh.mi_learning_backend.app.schedule.controller.dto;

import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface ScheduleMapper {


    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Schedule toSchedule(ScheduleRequest request);
}
