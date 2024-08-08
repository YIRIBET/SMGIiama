package com.example.SMGI.controller.user.Dto;

import com.example.SMGI.model.rol.RolBean;
import com.example.SMGI.model.user.UserBean;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private String username;
    private String password;
    private RolBean rolBean;

    public UserBean toEntity() {
        return new UserBean(id, name, lastname, surname, username, password, rolBean);
    }




}
