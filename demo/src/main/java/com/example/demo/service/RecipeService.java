package com.example.demo.service;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
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
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    //get all recipes
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }
    //get recipe by id
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }
    //add recipe
    public void addRecipe(Recipe recipe) {
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
