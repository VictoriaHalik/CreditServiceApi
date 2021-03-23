package com.example.CreditServiceApi.repo;

import com.example.CreditServiceApi.domain.additionalEntity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}