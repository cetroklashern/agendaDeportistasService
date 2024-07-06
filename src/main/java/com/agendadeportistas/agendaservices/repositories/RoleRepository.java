package com.agendadeportistas.agendaservices.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agendadeportistas.agendaservices.entities.RolesEntity;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity, Long>{
    Optional<RolesEntity> findByName(String name);
}
