package com.example.demo.service;

import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.model.Recipe;
import com.example.demo.repository.AuthRoleRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
        //=> INSERT INTO recipes (..,..,) VALUES ('','','')
    }

    //update recipe
    public void updateRecipe(Recipe updateRecipe, Long recipeId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isEmpty()) {
            throw new RecipeNotFoundException("recipe not found");
        } else {
            Recipe recipe = recipeOptional.get();
            recipe.setRecipeName(updateRecipe.getRecipeName());
            recipe.setRecipeIngredient(updateRecipe.getRecipeIngredient());
            recipe.setRecipeDescription(updateRecipe.getRecipeDescription());
        }

    }
    //delete delete
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    //getRecipesByUserId

    //blockRecipes

}
