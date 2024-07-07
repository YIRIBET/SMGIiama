package com.example.SMGI.model.product;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProductRepository {
    Optional<ProductBean> findByName(String name);
    List<ProductBean> findAll();
}
