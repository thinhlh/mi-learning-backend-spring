package com.thinhlh.mi_learning_backend.app.course.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RecommendationCourseResponse {
    private UUID id;
    private String background;
    private String title;
    private double rating;
    private double price;
    private String category;
    private String teacherName;
}
