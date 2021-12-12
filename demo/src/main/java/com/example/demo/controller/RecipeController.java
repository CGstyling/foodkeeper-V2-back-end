package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;
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
public class RecipeController {

    @Autowired
    private final RecipeService recipeService;
    private final UserService userService;
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/recipes")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/publicRecipes")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Recipe> getAllPublicRecipes() {
        return recipeService.getAllPublicRecipes();
    }

    @GetMapping("/recipes/{recipeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Optional<Recipe> getRecipeById(@PathVariable Long recipeId) {
        return recipeService.getRecipeById(recipeId);
    }

    @PostMapping("/recipes")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Object> addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok("recept is aangemaakt");
    }

    @DeleteMapping("/recipes/{recipeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }

    @PutMapping("/recipes/{recipeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
        recipeService.updateRecipe(recipe, recipeId);
    }

    @PatchMapping("/recipes/{recipeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void blockRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
        recipeService.blockRecipe(recipe, recipeId);
    }

    @GetMapping("/recipes/{recipeId}/comments")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity getCommentsByRecipeId(@PathVariable Long recipeId) {
        Iterable<Comment> recipeComments = recipeService.getCommentsByRecipeId(recipeId);
        return ResponseEntity.ok(recipeComments);
    }
}
