package com.example.SMGI.controller.category.dto;

import com.example.SMGI.model.category.CategoryBean;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String categoryName;

    public CategoryBean toEntity(){
        return new CategoryBean(id,categoryName);
    }
}
