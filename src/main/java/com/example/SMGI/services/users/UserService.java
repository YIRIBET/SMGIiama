package com.example.SMGI.services.users;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.model.rol.RolRepository;
import com.example.SMGI.model.user.UserBean;
import com.example.SMGI.model.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class UserService {
    private final UserRepository repository;
    private RolRepository rolRepository;

    //Save
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(UserBean user) {
        Optional<UserBean> found = repository.findById(user.getId());
        if (found.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El usuario ya existe"), HttpStatus.BAD_REQUEST);
        } else {
            repository.save(user);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Registrado correctamente"), HttpStatus.OK);
        }
        // return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"No se encontro"), HttpStatus.BAD_REQUEST);
    }


    //Update
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity <ApiResponse> update (UserBean user) {
        Optional<UserBean> found = repository.findById(user.getId());
        if (found.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(user),HttpStatus.OK,false,"Actualizar"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true,"NO ENCONTRADO"), HttpStatus.NOT_FOUND);

    }

    //Delete
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity <ApiResponse> delete (Long id) {
        Optional <UserBean> UserOptional = repository.findById(id);
        if (UserOptional.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,true,"Eliminado"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"No se puede eliminar el usuario"), HttpStatus.BAD_REQUEST);
    }

    //getAll
    public ResponseEntity <ApiResponse> findAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),HttpStatus.OK),HttpStatus.OK);
    }



}