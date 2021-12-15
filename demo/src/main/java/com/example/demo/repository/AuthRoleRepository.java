package com.example.demo.repository;

import com.example.demo.model.AuthRole;
import com.example.demo.model.EAuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
    Optional<AuthRole> findByRoleName(EAuthRole name);
}
