package com.example.demo.service;

import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.AuthRoleRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

     public List<Recipe> getAllPublicRecipes() {
         List<Recipe> recipeList = recipeRepository.findAll();
         recipeList.removeIf(Recipe::isRecipeIsPrivate);
         recipeList.removeIf(Recipe::isBlockRecipe);
         return recipeList;
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
       recipe = recipeRepository.save(recipe);
       return(recipe);
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
            recipe.setRecipeIsPrivate(updateRecipe.isRecipeIsPrivate());
            recipeRepository.save(recipe);
        }
    }

    //delete delete
    public void deleteRecipe(Long recipeId) {
        if(!recipeRepository.existsById(recipeId)){
            throw new RecipeNotFoundException("Recipe not found");
        }else {
            recipeRepository.deleteById(recipeId);
        }
    }

    //block recipe (admin only)
    public void blockRecipe(Recipe blockRecipe, Long recipeId) {

        Optional<Recipe> blockOptionalRecipe = recipeRepository.findById(recipeId);

        if(blockOptionalRecipe.isEmpty()) {
            throw new RecipeNotFoundException("recipe not found");
        } else {
            Recipe recipe = blockOptionalRecipe.get();
            recipe.setBlockRecipe(blockRecipe.isBlockRecipe());
            recipeRepository.save(recipe);
        }
    }

    //getCommentsFromRecipeById
    public Iterable<Comment> getCommentsByRecipeId(Long recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if(recipe.isPresent()) {
            if(recipe.get().isBlockRecipe())
            {
                List<Comment> filteredCommentList = new ArrayList<>();
                for (Comment comment : recipe.get().getComments()) {
                    Set<AuthRole> authRoleSet = comment.getUser().getAuthRoles();
                    for (AuthRole authRole : authRoleSet) {
                        if(authRole.getRoleName().equals(EAuthRole.ROLE_ADMIN))
                        {
                            filteredCommentList.add(comment);
                            break;
                        }
                    }
                }
                return filteredCommentList;
            }
            return recipe.get().getComments();
        } else {
            throw new RuntimeException("Recipe with all the comments is not found");
        }
    }





}
