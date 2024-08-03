package com.example.SMGI.controller.productRequest.dto;

import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.departament.DepartmentBean;
import com.example.SMGI.model.product.ProductBean;
import com.example.SMGI.model.productReq.ProductRequestBean;
import lombok.Data;

@Data
public class DtoProductRequest {
    private Long id;
    private String state;
    private String newName;
    private String newDescription;
    private ProductBean productBean;
    private CategoryBean categoryBean;
    private DepartmentBean departmentBean;
    private String reqType;

    public ProductRequestBean toEntity(){
        return new ProductRequestBean(id,state,newName,newDescription,productBean,categoryBean,departmentBean,reqType);
    }

}
