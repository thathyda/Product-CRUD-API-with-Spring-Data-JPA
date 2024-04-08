package com.practiceistad.mvc_taskwork.repository;

import com.practiceistad.mvc_taskwork.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //optional
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName (String name);
}
