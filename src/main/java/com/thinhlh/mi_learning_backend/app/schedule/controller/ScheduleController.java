package com.thinhlh.mi_learning_backend.app.schedule.controller;

import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.GetSchedulesOfDateRequest;
import com.thinhlh.mi_learning_backend.app.schedule.domain.usecase.GetDatesHasSchedulesUseCase;
import com.thinhlh.mi_learning_backend.app.schedule.domain.usecase.GetSchedulesOfDateUseCase;
import com.thinhlh.mi_learning_backend.base.BaseController;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ServletHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController extends BaseController {

    private final GetDatesHasSchedulesUseCase getDatesHasSchedulesUseCase;
    private final GetSchedulesOfDateUseCase getSchedulesOfDateUseCase;

    @GetMapping("/schedules/dates")
    private ResponseEntity<BaseResponse<List<LocalDate>>> getDatesHasSchedules(HttpServletRequest request) {
        return successResponse(getDatesHasSchedulesUseCase.invoke(ServletHelper.retrieveUsernameAndRolesFromRequest(request, null).getFirst()));
    }

    @GetMapping("/schedules")
    private ResponseEntity<BaseResponse<Object>> getSchedulesOfDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, HttpServletRequest request) {
        return successResponse(
                getSchedulesOfDateUseCase.invoke(
                        new GetSchedulesOfDateRequest(
                                ServletHelper
                                        .retrieveUsernameAndRolesFromRequest(request, () -> logger.debug("Invalid token"))
                                        .getFirst()
                                , date)
                ));
    }

}
