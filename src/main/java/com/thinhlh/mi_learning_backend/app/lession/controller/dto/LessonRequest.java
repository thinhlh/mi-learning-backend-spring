package com.thinhlh.mi_learning_backend.app.lession.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonRequest {
    @NotBlank
    private String title;

    @NotNull
    private UUID sectionId;

    @URL
    private String videoUrl;

    private Integer length;

    private Integer lessonOrder;

    @NotNull
    private boolean isVideo;
}
