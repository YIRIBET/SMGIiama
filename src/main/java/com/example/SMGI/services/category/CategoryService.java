package com.example.SMGI.services.category;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.category.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository repository;

    //save
    public ResponseEntity<ApiResponse> saveCategory(CategoryBean categoryBean){
        if (categoryBean.getCategoryName() == null)
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"categoría vacia"), HttpStatus.BAD_REQUEST);
        Optional<CategoryBean> foundCategory = repository.findCategoryBeanByCategoryName(categoryBean.getCategoryName());

        if (foundCategory.isPresent())

        if (foundCategory.isEmpty())

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"categoría ya registrada"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(categoryBean),HttpStatus.OK,"guardado con exito"),HttpStatus.OK);
    }

    //findAll
    public ResponseEntity<ApiResponse>getAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),HttpStatus.OK),HttpStatus.OK);
    }

    //findOne
    public ResponseEntity<ApiResponse>findOne(Long id){
        Optional<CategoryBean> foundCategory = repository.findById(id);
        if (foundCategory.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se encontró la categoría"),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(new ApiResponse(repository.findById(id),HttpStatus.OK),HttpStatus.OK);
    }
    //delete
    public ResponseEntity<ApiResponse> delete(Long id){
        Optional<CategoryBean> foundCategory = repository.findById(id);
        if (foundCategory.isEmpty())

        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se encontró la categoría a eliminar"),HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"categoria eliminada con exito"),HttpStatus.OK);

    }

}
