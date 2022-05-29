package com.thinhlh.mi_learning_backend.app.lession.controller.dto;

import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.TestLesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.VideoLesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface LessonMapper {


    @Mapping(target = "order", source = "lessonOrder")
    @Mapping(target = "video", source = "videoLesson")
    @Mapping(target = "test", source = "testLesson")
    LessonResponse toResponse(Lesson lesson);

    TestLessonResponse toTestLessonResponse(TestLesson lesson);

    @Mapping(target = "url", source = "videoUrl")
    VideoLessonResponse toVideoLessonResponse(VideoLesson lesson);


    @Mapping(target = "videoLesson", ignore = true)
    @Mapping(target = "testLesson", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Lesson toLesson(LessonRequest request);

}
