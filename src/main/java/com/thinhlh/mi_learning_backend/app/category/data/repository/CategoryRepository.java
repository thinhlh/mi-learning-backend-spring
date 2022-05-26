package com.thinhlh.mi_learning_backend.app.category.data.repository;

import com.thinhlh.mi_learning_backend.app.category.domain.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {


    Category findByTitle(String title);

}
