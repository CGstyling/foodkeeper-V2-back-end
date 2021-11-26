package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserRepository userRepository;
    public userDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
