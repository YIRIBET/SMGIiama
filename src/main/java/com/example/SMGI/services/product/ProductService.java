package com.example.SMGI.services.product;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.category.CategoryRepository;
import com.example.SMGI.model.departament.DepartmentBean;
import com.example.SMGI.model.departament.DepartmentRepository;
import com.example.SMGI.model.product.ProductBean;
import com.example.SMGI.model.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;
    private CategoryRepository categoryRepository;
    private DepartmentRepository departmentRepository;

    //save
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> save(ProductBean productBean ) {
        Optional<CategoryBean> foundCategory = categoryRepository.findById(productBean.getCategoryBean().getId());
        if (foundCategory.isEmpty())
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se econtró la categoría relacionada al producto"), HttpStatus.NOT_FOUND);
        if (productBean.getName() ==  null)
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"nombre nulo"), HttpStatus.BAD_REQUEST);
        Set<DepartmentBean> validDepartments = new HashSet<>();

        // Verificar que cada departamento asignado al producto existe
        for (DepartmentBean department : productBean.getDepartmentBeans()) {
            Optional<DepartmentBean> foundDepartment = departmentRepository.findById(department.getId());
            if (foundDepartment.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el departamento con ID: " + department.getId()), HttpStatus.NOT_FOUND);
            }

            validDepartments.add(foundDepartment.get());
            foundDepartment.get().getProductBeans().add(productBean);
        }
        productBean.setDepartmentBeans(validDepartments); // Establecer los departamentos válidos
        return new ResponseEntity<>(new ApiResponse(repository.save(productBean),HttpStatus.OK,"Guardado correctamente"),HttpStatus.OK);
    }

    //getAll
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),HttpStatus.OK),HttpStatus.OK);
    }

    //findAll
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> findOne(Long id){
        Optional<ProductBean> foundProduct = repository.findById(id);
        if (foundProduct.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"no se econtró el producto"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(new ApiResponse(foundProduct,HttpStatus.OK),HttpStatus.OK);

    }


    //find by Department
    public ResponseEntity<ApiResponse> findByDepartmentId(Long departmentId) {
        Optional<DepartmentBean> foundDepartment = departmentRepository.findById(departmentId);
        if (foundDepartment.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el departamento"), HttpStatus.NOT_FOUND);
        }

        List<ProductBean> products = repository.findByDepartmentBeans(foundDepartment.get());
        return new ResponseEntity<>(new ApiResponse(products, HttpStatus.OK, "Productos encontrados correctamente"), HttpStatus.OK);
    }
}
