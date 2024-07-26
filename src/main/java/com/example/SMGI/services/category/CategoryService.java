package com.example.SMGI.services.category;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.category.CategoryRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {SQLException.class})
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository repository;

    //save
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> saveCategory(CategoryBean categoryBean){
        if (categoryBean.getCategoryName() == null)
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"categoría vacia"), HttpStatus.BAD_REQUEST);
        Optional<CategoryBean> foundCategory = repository.findByCategoryName(categoryBean.getCategoryName());

        if (foundCategory.isPresent())

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"categoría ya registrada"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(categoryBean),HttpStatus.OK,"guardado con exito"),HttpStatus.OK);
    }

    //findAll
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse>getAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),HttpStatus.OK),HttpStatus.OK);
    }

    //findOne
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse>findOne(Long id){
        Optional<CategoryBean> foundCategory = repository.findById(id);
        if (foundCategory.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se encontró la categoría"),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(new ApiResponse(repository.findById(id),HttpStatus.OK),HttpStatus.OK);
    }
    //delete
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id){
        Optional<CategoryBean> foundCategory = repository.findById(id);
        if (foundCategory.isEmpty())

        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se encontró la categoría a eliminar"),HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"categoria eliminada con exito"),HttpStatus.OK);

    }

}
