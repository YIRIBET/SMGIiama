package com.example.SMGI.controller.category;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.controller.category.dto.CategoryDto;
import com.example.SMGI.services.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@CrossOrigin({"*"})
public class CategoryController {
    private CategoryService service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody CategoryDto dto){
        return service.saveCategory(dto.toEntity());
    }


    @GetMapping("/findOne/{id}")
    public ResponseEntity<ApiResponse> findOne(@PathVariable Long id){
        return service.findOne(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping ("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody CategoryDto dto){
        return service.update(dto.toEntity());
    }

}
