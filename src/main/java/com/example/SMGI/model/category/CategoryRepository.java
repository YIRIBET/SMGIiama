package com.example.SMGI.model.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <CategoryBean, Long>{


    //find by name
    Optional<CategoryBean> findCategoryBeanByCategoryName(String s);



}
