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
import java.util.stream.Collectors;

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


    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

     public List<Recipe> getAllPublicRecipes() {
         List<Recipe> recipeList = recipeRepository.findAll();
         recipeList.removeIf(Recipe::isRecipeIsPrivate);
         recipeList.removeIf(Recipe::isBlockRecipe);
         return recipeList;
    }

    public Optional<Recipe> getRecipeById(Long recipeId) {
        if(!recipeRepository.existsById(recipeId)){
            throw new RecipeNotFoundException("Sorry, Recipe is not found");
        }
        return recipeRepository.findById(recipeId);
    }

    public Recipe addRecipe(Recipe recipe) {
       recipe = recipeRepository.save(recipe);
       return(recipe);
    }

    public void updateRecipe(Recipe updateRecipe, Long recipeId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isEmpty()) {
            throw new RecipeNotFoundException("Sorry, recipe not found");
        } else {
            Recipe recipe = recipeOptional.get();
            recipe.setRecipeName(updateRecipe.getRecipeName());
            recipe.setRecipeIngredient(updateRecipe.getRecipeIngredient());
            recipe.setRecipeDescription(updateRecipe.getRecipeDescription());
            recipe.setRecipeIsPrivate(updateRecipe.isRecipeIsPrivate());
            recipeRepository.save(recipe);
        }
    }

    public void deleteRecipe(Long recipeId) {
        if(!recipeRepository.existsById(recipeId)){
            throw new RecipeNotFoundException("Sorry, Recipe not found");
        }else {
            recipeRepository.deleteById(recipeId);
        }
    }

    public void blockRecipe(Recipe blockRecipe, Long recipeId) {

        Optional<Recipe> blockOptionalRecipe = recipeRepository.findById(recipeId);

        if(blockOptionalRecipe.isEmpty()) {
            throw new RecipeNotFoundException("Sorry, recipe not found");
        } else {
            Recipe recipe = blockOptionalRecipe.get();
            recipe.setBlockRecipe(blockRecipe.isBlockRecipe());
            recipeRepository.save(recipe);
        }
    }

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
                 return filteredCommentList.stream()
                    .distinct()
                    .collect( Collectors.toList() );
            }
            return recipe.get().getComments().stream()
                    .distinct()
                    .collect( Collectors.toList() );
        } else {
            throw new RuntimeException("Recipe with the comments from the recipe is not found");
        }
    }
}
