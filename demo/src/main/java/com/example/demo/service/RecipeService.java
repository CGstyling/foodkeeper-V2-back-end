package com.example.demo.service;

import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.AuthRoleRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final AuthRoleRepository authRoleRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, AuthRoleRepository authRoleRepository) {
        this.userRepository = userRepository;
        this.authRoleRepository = authRoleRepository;
        this.recipeRepository = recipeRepository;
    }

    //get all recipes
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    //get recipe by id
    public Optional<Recipe> getRecipeById(Long recipeId) {
        if(!recipeRepository.existsById(recipeId)){
            throw new RecipeNotFoundException("Recipe is not found");
        }
        return recipeRepository.findById(recipeId);
    }

    //add recipe
    public void createRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
    //update user
    public void updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
    //delete user
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

}
