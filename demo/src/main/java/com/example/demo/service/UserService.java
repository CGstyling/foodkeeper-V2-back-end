package com.example.demo.service;

import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Sorry, user not found");
        }
        return userRepository.findById(userId);
    }

    public void updateUser(Long userId, User updateUser) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("Sorry, user not found");
        } else {
            User user = userOptional.get();
            user.setUsername(updateUser.getUsername());
            user.setEmail(updateUser.getEmail());
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            userRepository.save(user);
        }
    }

    public Iterable<Recipe> getRecipesByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            List<Recipe> recipeList = user.get().getRecipes();
            return recipeList.stream()
                    .distinct()
                    .collect( Collectors.toList() );
        }else {
            throw new RecipeNotFoundException("Sorry, we could not find your recipes. Please try again!");
        }
    }
}
