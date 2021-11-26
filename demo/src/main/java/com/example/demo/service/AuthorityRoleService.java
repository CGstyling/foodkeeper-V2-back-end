package com.example.demo.service;

import com.example.demo.repository.AuthorityRoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityRoleService {

    @Autowired
    AuthorityRoleRepository authorityRoleRepository;
    UserRepository userRepository;
    public AuthorityRoleService (AuthorityRoleRepository authorityRoleRepository, UserRepository userRepository) {
        this.authorityRoleRepository = authorityRoleRepository;
        this.userRepository = userRepository;
    }



}
