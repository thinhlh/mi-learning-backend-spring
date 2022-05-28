package com.thinhlh.mi_learning_backend.app.student_course.domain.service;

import java.util.UUID;

public interface StudentCourseService {

    Boolean studentJoinCourse(String email, UUID courseId);

}
