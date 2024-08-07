package com.example.SMGI.controller.department.dto;

import com.example.SMGI.model.departament.DepartmentBean;
import lombok.Data;

@Data
public class DepartmentDto {
    private String name;
    private String address;
    private Long id;

    public DepartmentBean toEntity (){
        return new DepartmentBean(id,name,address);
    }
}
