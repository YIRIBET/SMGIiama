package com.example.SMGI.controller.product;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.controller.product.dto.ProductDto;
import com.example.SMGI.services.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
@CrossOrigin({"*"})
public class ProductController {
    private ProductService service;


    //findALL
    @GetMapping("/")
    public ResponseEntity<ApiResponse> findAll(){
        return service.findAll();
    }
    //findOne
    @GetMapping("/findOne/{id}")
    public ResponseEntity<ApiResponse> findOne(@PathVariable Long id){
        return service.findOne(id);
    }
    //save
    @PostMapping("/save")
    public ResponseEntity<ApiResponse>save(@RequestBody ProductDto dto){
        return service.save(dto.toEntity());
    }
    // encontrar productos según su departamento
    @GetMapping("/getByDepartmentId/{id}")
    public ResponseEntity<ApiResponse> getByDepartment(@PathVariable Long id){
        return service.findByDepartmentId(id);
    }

    //update
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody ProductDto dto){
        return service.update(dto.toEntityUpdate());
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete (@PathVariable Long id){
        return service.delete(id);
    }


}
