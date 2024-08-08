package com.example.SMGI.controller.productRequest;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.controller.productRequest.dto.DtoProductRequest;
import com.example.SMGI.services.productRequest.ProductRequestServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/productRequest")
@AllArgsConstructor
public class ProductRequestController {

    private final ProductRequestServices services;


    @PostMapping("/saveUpdateReq")
    public ResponseEntity<ApiResponse> saveRequestTest (@RequestBody DtoProductRequest dtoProductRequest){
        return services.saveReq(dtoProductRequest.toEntity());
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> findAllProductRequest(){
        return services.findAllProductRequest();
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<ApiResponse> approveProductRequest (@PathVariable Long id){
        return services.approveRequest(id);
    }
}
