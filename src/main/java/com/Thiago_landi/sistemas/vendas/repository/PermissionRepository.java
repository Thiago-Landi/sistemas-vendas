package com.Thiago_landi.sistemas.vendas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Thiago_landi.sistemas.vendas.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    
    
    Optional<Permission> findByDescription(String description); 

}
