package com.thinhlh.mi_learning_backend.app.section.data.repository;

import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SectionRepository extends CrudRepository<Section, UUID> {

    List<Section> findAllByCourse_Id(UUID course_id);
}
