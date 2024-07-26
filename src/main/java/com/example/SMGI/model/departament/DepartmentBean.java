package com.example.SMGI.model.departament;

import com.example.SMGI.model.product.ProductBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String address;

    public DepartmentBean(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="department_product",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<ProductBean> productBeans;


}
