package com.thinhlh.mi_learning_backend.app.schedule.data.repository;

import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, UUID> {

    List<Schedule> getSchedulesByStudent_User_Email(String email);
}
