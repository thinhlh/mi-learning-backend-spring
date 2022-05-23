package com.thinhlh.mi_learning_backend.app.lession.controller.dto;

import com.thinhlh.mi_learning_backend.app.lession.domain.entity.Lesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.TestLesson;
import com.thinhlh.mi_learning_backend.app.lession.domain.entity.VideoLesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {


    @Mapping(target = "video", source = "videoLesson")
    @Mapping(target = "test", source = "testLesson")
    LessonResponse toResponse(Lesson lesson);

    TestLessonResponse toTestLessonResponse(TestLesson lesson);

    @Mapping(target = "url", source = "videoUrl")
    VideoLessonResponse toVideoLessonResponse(VideoLesson lesson);

}
