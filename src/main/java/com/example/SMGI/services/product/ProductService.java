package com.example.SMGI.services.product;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.category.CategoryRepository;
import com.example.SMGI.model.product.ProductBean;
import com.example.SMGI.model.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;
    private CategoryRepository categoryRepository;

    //save
    public ResponseEntity<ApiResponse> save(ProductBean productBean ) {
        System.err.println("id de categoria "+productBean.getCategoryBean().getId());
        Optional<CategoryBean> foundCategory = categoryRepository.findById(productBean.getCategoryBean().getId());
        if (foundCategory.isEmpty())
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se econtró la categoría relacionada al producto"), HttpStatus.NOT_FOUND);
        if (productBean.getName() ==  null)
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"nombre nulo"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(repository.save(productBean),HttpStatus.OK,"Guardado correctamente"),HttpStatus.OK);
    }

    //getAll
    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),HttpStatus.OK),HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> findOne(Long id){
        Optional<ProductBean> foundProduct = repository.findById(id);
        if (foundProduct.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se econtró el producto"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(new ApiResponse(repository.findById(id),HttpStatus.OK),HttpStatus.OK);

    }
}
