package com.example.demo.repository;

import com.example.demo.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {

//    Optional<AuthorityRole> findByName(EAuthorityRole name); //make methods in auth controller/service
}
