package com.example.SMGI.model.product;

import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.departament.DepartmentBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
 /*   @Column(length = 50, nullable = false)
    private String status;*/
    @Column(length = 50, nullable = false)
    private String caracteristicas;
  /*  @Column(length = 50, nullable = false)
    private String valor;*/

    //crear tabla intermedia para departamento


    public ProductBean(String name, String caracteristicas, CategoryBean categoryBean) {
        this.name = name;
        this.caracteristicas = caracteristicas;
        this.categoryBean = categoryBean;
    }

    @ManyToMany(mappedBy = "productBeans")
    private Set<DepartmentBean> departmentBeans;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryBean categoryBean;

}
