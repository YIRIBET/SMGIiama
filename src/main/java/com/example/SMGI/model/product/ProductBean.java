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
    private String description;
  /*  @Column(length = 50, nullable = false)
    private String valor;*/

    //crear tabla intermedia para departamento


    public ProductBean(String name, String description, CategoryBean categoryBean, Set<DepartmentBean> departmentBeans) {
        this.name = name;
        this.description = description;
        this.categoryBean = categoryBean;
        this.departmentBeans = departmentBeans;
    }

    public ProductBean(Long id,String name, String description, CategoryBean categoryBean, Set<DepartmentBean> departmentBeans) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryBean = categoryBean;
        this.departmentBeans = departmentBeans;
    }

    @ManyToMany(mappedBy = "productBeans")
    private Set<DepartmentBean> departmentBeans;

    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryBean categoryBean;

}
