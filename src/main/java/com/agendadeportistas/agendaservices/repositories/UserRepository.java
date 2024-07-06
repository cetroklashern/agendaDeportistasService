package com.agendadeportistas.agendaservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agendadeportistas.agendaservices.entities.UsuariosEntity;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UsuariosEntity, Long>{
    Optional<UsuariosEntity> findByEmail(String email);
    Optional<UsuariosEntity> findByNombre(String nombre);
    Optional<UsuariosEntity> findByUsername(String username);
    Boolean existsByNombre(String nombre);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
