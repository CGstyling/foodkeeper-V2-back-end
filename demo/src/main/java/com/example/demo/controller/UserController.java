package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
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

    @PutMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }

    @GetMapping("/users/{userId}/recipes")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity getRecipesByUserId(@PathVariable Long userId) {
        Iterable<Recipe> userRecipes = userService.getRecipesByUserId(userId);
        return ResponseEntity.ok(userRecipes);
    }
}
