package com.example.SMGI.model.product;

import com.example.SMGI.model.category.CategoryBean;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String status;
    @Column(length = 50, nullable = false)
    private String caracteristicas;
    @Column(length = 50, nullable = false)
    private String valor;

    //crear tabla intermedia para departamento


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryBean categoryBean;

}
