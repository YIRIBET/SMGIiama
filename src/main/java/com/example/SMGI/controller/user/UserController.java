package com.example.SMGI.controller.user;

import com.example.SMGI.config.ApiResponse;
import com.example.SMGI.controller.user.Dto.UserDto;
import com.example.SMGI.model.user.UserBean;
import com.example.SMGI.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin({"*"})
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/crear/")
    public ResponseEntity<ApiResponse> save(@RequestBody UserDto dto) {
        UserBean user = dto.toEntity();
        return service.save(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UserDto dto) {
        UserBean user = dto.toEntity();
        user.setId(id);
        return service.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> findAll() {
        return service.findAll();
    }
}
