package com.thinhlh.mi_learning_backend.app.schedule.domain.service;

import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.GetSchedulesOfDateRequest;
import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.ScheduleRequest;
import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    Schedule createSchedule(ScheduleRequest scheduleRequest);

    List<LocalDate> getDatesHasSchedule(String email);

    List<Schedule> getSchedulesOfDate(GetSchedulesOfDateRequest date);

}
