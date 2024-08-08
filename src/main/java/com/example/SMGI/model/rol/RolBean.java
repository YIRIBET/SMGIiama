package com.example.SMGI.model.rol;

import com.example.SMGI.model.productReq.ProductRequestBean;
import com.example.SMGI.model.user.UserBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor


public class RolBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rolId;
     @Column(length = 45,nullable = false)
    private String rolName;

    @JsonIgnore
    @OneToMany(mappedBy = "rolBean", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<UserBean> userBeans;

   
}
