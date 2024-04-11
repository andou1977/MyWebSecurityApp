package com.example.mywebsecurityapp.repository;

import com.example.mywebsecurityapp.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    RoleEntity findByrolename(String roleName);
}
