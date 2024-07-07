package com.example.SMGI.model.category;

import com.example.SMGI.model.product.ProductBean;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class CategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "categoryBean")
    private Set<ProductBean> productBeans;
}
