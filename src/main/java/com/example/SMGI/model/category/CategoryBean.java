package com.example.SMGI.model.category;

import com.example.SMGI.model.product.ProductBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
public class CategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    public CategoryBean(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "categoryBean")
    private Set<ProductBean> productBeans;


}
