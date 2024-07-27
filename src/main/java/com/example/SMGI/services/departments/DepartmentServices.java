package com.example.SMGI.services.departments;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.model.departament.DepartmentBean;
import com.example.SMGI.model.departament.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = SQLException.class)
public class DepartmentServices {
    private final DepartmentRepository repository;


    //save
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> createDepartment(DepartmentBean departmentBean){
        Optional<DepartmentBean> foundDepartment = repository.findByName(departmentBean.getName());
        if (foundDepartment.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"Departamento ya registrado"), HttpStatus.BAD_REQUEST);
        Optional<DepartmentBean> foundAddress = repository.findByAddress( departmentBean.getAddress());
        if (foundAddress.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"Un departamento ya que cuenta con esta dirección"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ApiResponse(repository.save(departmentBean),HttpStatus.OK,"Guardado con éxito"),HttpStatus.OK);
    }

    //findAll
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),HttpStatus.OK),HttpStatus.OK);
    }

    //findOne
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> findOne(Long id){
        Optional<DepartmentBean> foundDepartment = repository.findById(id);

        if (foundDepartment.isEmpty())
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"Departamento NO ENCONTRADO"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse(foundDepartment,HttpStatus.OK),HttpStatus.OK);
    }


    //Delete
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> delete(Long id){
        Optional<DepartmentBean> foundDepartment = repository.findById(id);
        if (foundDepartment.isEmpty())
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"Departamento NO ENCONTRADO"), HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(foundDepartment,HttpStatus.OK,"eliminado con exito"),HttpStatus.OK);
    }

    //update
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse>update (DepartmentBean departmentBean){
        Optional<DepartmentBean> foundDepartment = repository.findById(departmentBean.getId());
        if (foundDepartment.isEmpty())
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"Departamento NO ENCONTRADO"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(departmentBean),HttpStatus.OK,false,"departamento actualizado con exito"),HttpStatus.OK);

    }

}
