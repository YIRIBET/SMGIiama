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

    @GetMapping("/")
    public ResponseEntity<ApiResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<ApiResponse> findOne(@PathVariable Long id){
        return service.findOne(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse>save(@RequestBody ProductDto dto){
        System.err.println(dto.toString());
        return service.save(dto.toEntity());
    }
}
