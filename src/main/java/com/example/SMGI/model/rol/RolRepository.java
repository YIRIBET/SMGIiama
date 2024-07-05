package com.example.SMGI.model.rol;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository {
    Optional<RolBean> findByRolName(String string);
    Optional<RolBean> findByRolId(Long id);
}
