package com.example.SMGI.model.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolBean, Long> {
    Optional<RolBean> findByRolName(String rolName);

}

