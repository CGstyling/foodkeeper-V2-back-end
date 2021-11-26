package com.example.demo.service;

import com.example.demo.repository.AuthRoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthRoleService {

    @Autowired
    AuthRoleRepository authRoleRepository;
    UserRepository userRepository;
    public AuthRoleService(AuthRoleRepository authRoleRepository, UserRepository userRepository) {
        this.authRoleRepository = authRoleRepository;
        this.userRepository = userRepository;
    }



}
