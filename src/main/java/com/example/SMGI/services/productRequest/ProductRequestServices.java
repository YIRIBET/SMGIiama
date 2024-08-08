package com.example.SMGI.services.productRequest;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.model.category.CategoryBean;
import com.example.SMGI.model.category.CategoryRepository;
import com.example.SMGI.model.departament.DepartmentBean;
import com.example.SMGI.model.departament.DepartmentRepository;
import com.example.SMGI.model.product.ProductBean;
import com.example.SMGI.model.product.ProductRepository;
import com.example.SMGI.model.productReq.ProductRequestBean;
import com.example.SMGI.model.productReq.ProductRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional(rollbackFor = SQLException.class)
@AllArgsConstructor
public class ProductRequestServices {

    private final ProductRequestRepository repository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;



    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse>saveReq(ProductRequestBean productRequest){
        String var = productRequest.getReqType();
        switch (var){
            case "POST":{
                //valida que la  categoria del producto que se intenta crear exista
                Optional<CategoryBean> foundCategory = categoryRepository.findById(productRequest.getCategoryBean().getId());
                if (foundCategory.isEmpty())
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"la categoría de el producto que solicitas crear no existe"),HttpStatus.NOT_FOUND);

                //valida que el  departamento del producto que se intenta crear exista
                Optional<DepartmentBean> foundDepartment = departmentRepository.findById(productRequest.getDepartmentBean().getId());
                if (foundDepartment.isEmpty())
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"el departamento de el producto que solicitas crear no existe"),HttpStatus.NOT_FOUND);

                productRequest.setState("pending");
                return new ResponseEntity<>(new ApiResponse(repository.save(productRequest),HttpStatus.OK,false,"solicitud de creación de producto enviada con éxito"),HttpStatus.OK);
            }
            case "UPDATE":{

                //valida que el producto que se solicita actualizar exista
                Optional<ProductBean> foundProduct = productRepository.findById(productRequest.getProductBean().getId());
                if (foundProduct.isEmpty())
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"El producto que solicitas actualizar no existe"),
                            HttpStatus.NOT_FOUND);

                //valida que la nueva categoria del producto que se intenta actualizar exista
                Optional<CategoryBean> foundCategory = categoryRepository.findById(productRequest.getCategoryBean().getId());
                if (foundCategory.isEmpty())
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"la categoría de el producto que solicitas actualizar no existe"),
                            HttpStatus.NOT_FOUND);

                //valida que el nuevo departamento del producto que se intenta actualizar exista
                Optional<DepartmentBean> foundDepartment = departmentRepository.findById(productRequest.getDepartmentBean().getId());
                if (foundDepartment.isEmpty())
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"el departamento de el producto que solicitas actualizar no existe"),
                            HttpStatus.NOT_FOUND);

                productRequest.setState("pending");
                return new ResponseEntity<>(new ApiResponse(repository.save(productRequest),HttpStatus.OK,false,"solicitud de actualización de "
                        +foundProduct.get().getName()+" enviada con éxito"),HttpStatus.OK);

            }
            case "DELETE":{

                Optional<ProductBean> foundProduct = productRepository.findById(productRequest.getProductBean().getId());
                if (foundProduct.isEmpty())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"El producto que solicitas eliminar no existe"),HttpStatus.NOT_FOUND);
                productRequest.setState("pending");
                return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(productRequest),HttpStatus.OK,"solicitud de eliminar producto enviada con exito"),HttpStatus.OK);

            }
            default:
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"error al realizar la solicitud"),HttpStatus.BAD_REQUEST);
        }
    }

    //encontrar solicitudes de productos
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> findAllProductRequest(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),HttpStatus.OK),HttpStatus.OK);
    }


    //aprobar una solicitud
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> approveRequest (Long id){
        Optional<ProductRequestBean> foundRequest = repository.findById(id);
        if (foundRequest.isEmpty())
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"La solicitud que intentas aprobar no existe"),
                HttpStatus.NOT_FOUND);

        //debido a problemas de recursividad en el manejo de solicitudes DELETE, se borra directamente en el
        //repositorio de producto
       if (foundRequest.get().getReqType().equals("DELETE")){
           productRepository.deleteById(foundRequest.get().getProductBean().getId());
           return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,false,"solcitud aprovada correctamente, se elimino el producto correctamente"),
                   HttpStatus.OK);
       }
            ProductRequestBean productRequestBean = foundRequest.get();
            productRequestBean.setState("approved");
            System.err.println(productRequestBean);
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(productRequestBean),HttpStatus.OK,false,"solicitud "
                    +foundRequest.get().getReqType() + " aprovada con exito"),HttpStatus.OK);
       }
}
