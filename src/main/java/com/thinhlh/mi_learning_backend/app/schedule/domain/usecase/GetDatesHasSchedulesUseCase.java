package com.thinhlh.mi_learning_backend.app.schedule.domain.usecase;

import com.thinhlh.mi_learning_backend.app.schedule.domain.service.ScheduleService;
import com.thinhlh.mi_learning_backend.base.BaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDatesHasSchedulesUseCase implements BaseUseCase<String, List<LocalDate>> {

    private final ScheduleService service;

    @Override
    public List<LocalDate> invoke(String data) throws RuntimeException {
        return service.getDatesHasSchedule(data);
    }
}
