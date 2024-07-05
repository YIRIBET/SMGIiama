package com.example.SMGI.model.rol;

import com.example.SMGI.model.user.UserBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RolBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
     @Column(length = 45,nullable = false)
    private String rolName;

     @JsonIgnore
    @OneToMany(mappedBy = "rol",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<UserBean> users;

}
