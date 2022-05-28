package com.thinhlh.mi_learning_backend.app.schedule.domain.usecase;

import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.GetSchedulesOfDateRequest;
import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.Schedule;
import com.thinhlh.mi_learning_backend.app.schedule.domain.service.ScheduleService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetSchedulesOfDateUseCase implements BaseUseCase<GetSchedulesOfDateRequest, List<Schedule>> {

    private final ScheduleService scheduleService;

    @Override
    public List<Schedule> invoke(GetSchedulesOfDateRequest data) throws RuntimeException {
        return scheduleService.getSchedulesOfDate(data);
    }
}
