package com.example.scm.repository;

import com.example.scm.domain.Role;
import com.example.scm.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByType(RoleType type);
}
