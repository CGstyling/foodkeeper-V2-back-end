package com.example.demo.service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.exception.UserNotFoundException;
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
    public Optional<User> getUserById(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        return userRepository.findById(userId);
    }

    //update user
    public void updateUser(Long userId, User newUser) {
        if(!userRepository.existsById(userId)) {
            throw new RecordNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        userRepository.save(user);
    }

    //delete user
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

}
