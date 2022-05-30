package com.thinhlh.mi_learning_backend.app.student_course.domain.service;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.StudentCourseResponse;
import com.thinhlh.mi_learning_backend.app.student_course.controller.dto.ToggleSaveCourseRequest;
import com.thinhlh.mi_learning_backend.app.student_course.domain.usecase.GetStudentCourseDetailParams;

import java.util.List;
import java.util.UUID;

public interface StudentCourseService {

    Boolean studentJoinCourse(String email, UUID courseId);

    Boolean checkStudentEnrolledCourse(String email, UUID courseId);

    StudentCourseResponse getStudentCourseDetail(GetStudentCourseDetailParams params);

    Boolean toggleSavedCourse(ToggleSaveCourseRequest request);

}
