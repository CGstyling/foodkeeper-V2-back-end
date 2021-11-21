package com.example.demo.service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Recipe> findAll() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    public Recipe findById(long nr) {
        Optional<Recipe> recipe = recipeRepository.findById(nr);
        if(recipe.isPresent()) {
            return recipe.get();
        } else {
            throw new RecordNotFoundException("Recipe with id " + nr + "not found");
        }
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteById(long nr){
        try {
            recipeRepository.deleteById(nr);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex);
            throw new RecordNotFoundException();
        }
    }

    public Iterable<User> getRecipeUsers(long nr) {
        Optional<Recipe> recipe = recipeRepository.findById(nr);
        if (recipe.isPresent()) {
            return recipe.get().getUsers();
        }
        else {
            throw new RecordNotFoundException("Team with id " + nr + " not found");
        }
    }
}
