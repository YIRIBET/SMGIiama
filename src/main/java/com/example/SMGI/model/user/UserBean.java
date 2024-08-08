package com.example.SMGI.model.user;

import com.example.SMGI.model.rol.RolBean;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter

@NoArgsConstructor
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 45,nullable = false)
    private String name;
    @Column(length = 45,nullable = false)
    private String lastname;
    @Column(length = 45,nullable = false)
    private String surname;
    @Column(length = 45,nullable = false)
    private String username;
    @Column(length = 45,nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "rolId")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "rolId"
    )
    private RolBean rol;

    public UserBean(long id, String name, String lastname, String surname, String username, String password, RolBean rol) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

}
