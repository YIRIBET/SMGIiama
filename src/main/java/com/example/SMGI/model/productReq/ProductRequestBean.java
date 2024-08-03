package com.example.SMGI.model.productReq;

import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.departament.DepartmentBean;
import com.example.SMGI.model.product.ProductBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "product_request")
@NoArgsConstructor
public class ProductRequestBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String newName;
    private String newDescription;
    private String reqType;
    private String reason;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductBean productBean;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryBean categoryBean;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentBean departmentBean;


    public ProductRequestBean(Long id, String state, String newName, String newDescription, ProductBean productBean, CategoryBean categoryBean, DepartmentBean departmentBean, String reqType) {
        this.id = id;
        this.state = state;
        this.newName = newName;
        this.newDescription = newDescription;
        this.productBean = productBean;
        this.categoryBean = categoryBean;
        this.departmentBean = departmentBean;
        this.reqType = reqType;
    }
}
