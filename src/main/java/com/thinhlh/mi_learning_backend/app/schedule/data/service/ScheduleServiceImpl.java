package com.thinhlh.mi_learning_backend.app.schedule.data.service;

import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.GetSchedulesOfDateRequest;
import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.ScheduleMapper;
import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.ScheduleRequest;
import com.thinhlh.mi_learning_backend.app.schedule.data.repository.ScheduleRepository;
import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.Schedule;
import com.thinhlh.mi_learning_backend.app.schedule.domain.service.ScheduleService;
import com.thinhlh.mi_learning_backend.app.student.data.repository.StudentRepository;
import com.thinhlh.mi_learning_backend.app.teacher.data.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ScheduleMapper mapper;

    @Override
    @Transactional
    public Schedule createSchedule(ScheduleRequest scheduleRequest) {
        var schedule = mapper.toSchedule(scheduleRequest);
        var student = studentRepository.findByUser_Email(scheduleRequest.getEmail());

        if (student != null) {
            schedule.setStudent(student);

            schedule = scheduleRepository.save(schedule);

            student.getSchedules().add(schedule);

            return schedule;
        }
        return null;
    }

    @Override
    @Transactional
    public List<LocalDate> getDatesHasSchedule(String email) {
        final List<Schedule> schedules = scheduleRepository.getSchedulesByStudent_User_Email(email);

        final List<LocalDate> datesHasSchedule = new ArrayList<>();

        schedules.forEach(schedule -> {
            var dueDateTime = schedule.getDueDate();
            var dueDate = LocalDate.of(dueDateTime.getYear(), dueDateTime.getMonth(), dueDateTime.getDayOfMonth());


            if (!datesHasSchedule.contains(dueDate)) {
                datesHasSchedule.add(dueDate);
            }
        });

        return datesHasSchedule;
    }

    @Override
    @Transactional
    public List<Schedule> getSchedulesOfDate(GetSchedulesOfDateRequest request) {
        var schedulesOfStudent = scheduleRepository.getSchedulesByStudent_User_Email(request.getEmail());
        var result = new ArrayList<Schedule>();

        schedulesOfStudent.forEach(schedule -> {
            if (schedule.getDueDate().toLocalDate().isEqual(request.getDate())) {
                result.add(schedule);
            }
        });

        return result;
    }
}
