package com.example.demo.service;

import com.example.demo.dto.request.UserSignUpRequest;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.User;
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

    public void registerUser(UserSignUpRequest userSignUpRequest) {
        if(userRepository.existsByUsername(userSignUpRequest.getUsername())) {
            throw new RecordNotFoundException();
        }

        if(userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            throw new RecordNotFoundException();
        }

        User user = new User(userSignUpRequest.getUsername(), getEmail(), encoder.encode(userSignUpRequest.getPassword()));
    }



}
