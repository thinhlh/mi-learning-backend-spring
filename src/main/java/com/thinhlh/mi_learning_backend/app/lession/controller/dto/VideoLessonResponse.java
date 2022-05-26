package com.thinhlh.mi_learning_backend.app.lession.controller.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class VideoLessonResponse {
    private String url;
    private Integer length;
}
