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
        return service.createDepartment(dto.toEntity());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse>delete (@PathVariable Long id){
        return service.delete(id);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update (@RequestBody DepartmentDto dto){
        return service.update(dto.toEntity());
    }


}
