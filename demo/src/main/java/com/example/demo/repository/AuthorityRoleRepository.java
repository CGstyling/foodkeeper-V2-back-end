package com.example.demo.repository;

import com.example.demo.model.AuthorityRole;
import com.example.demo.model.EAuthorityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRoleRepository extends JpaRepository<AuthorityRole, Long> {

//    Optional<AuthorityRole> findByName(EAuthorityRole name); //make methods in auth controller/service
}
