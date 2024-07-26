package com.example.SMGI.controller.product.dto;

import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.departament.DepartmentBean;
import com.example.SMGI.model.product.ProductBean;
import lombok.Data;

import java.util.Set;

@Data
public class ProductDto {
    private String name;
    private String description;
    private CategoryBean categoryBean;
    private Set<DepartmentBean> departmentBeans;

    public ProductBean toEntity(){
        return new ProductBean(name,description, categoryBean, departmentBeans);
    }
}
