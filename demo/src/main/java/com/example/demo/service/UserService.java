package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    //get user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    //add user
    public void addUser(User user) {
        userRepository.save(user);
    }
    //update user
    public void updateUser(User user) {
        userRepository.save(user);
    }
    //delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
