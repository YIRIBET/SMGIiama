package com.example.SMGI.controller.department;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.controller.department.dto.DepartmentDto;
import com.example.SMGI.services.departments.DepartmentServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin({"*"})
@RequestMapping("/api/department")
public class DepartmentController {
    private DepartmentServices service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> findAll(){
        return service.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody DepartmentDto dto){
        System.err.println(dto.getId());
        return service.createDepartment(dto.toEntity());
    }


}
