package com.example.demo.service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAll() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }


    public long createUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    public void updateUser(long id, User newUser) {
        if (!userRepository.existsById(id)) {
            throw new RecordNotFoundException();
        }
        User user = userRepository.findById(id).get();
        user.setUserName(newUser.getUserName());
        user.setEmailAddress(newUser.getEmailAddress());
        user.setBiography(newUser.getBiography());
        userRepository.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println(ex);
            throw new RecordNotFoundException();
        }
    }

    public User findById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("Speler with id " + id + " not found");
        }
    }

    public boolean userExistsById(long id) {
        return userRepository.existsById(id);
    }

}
