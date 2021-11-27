package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foodkeeper")
public class UserController {

    @Autowired
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }
}
