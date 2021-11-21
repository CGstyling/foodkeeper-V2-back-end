package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity getRecipe() {
        Iterable<Recipe> recipes;
        recipes = recipeService.findAll();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/recipes/{nr}")
    public ResponseEntity getRecipe(@PathVariable long nr) {
        Recipe recipe = recipeService.findById(nr);
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/recipes")
    public ResponseEntity addRecipe(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
        return ResponseEntity.ok("You just added a new recipe");
    }

    @DeleteMapping("/recipes/{nr}")
    public ResponseEntity deleteRecipe(@PathVariable long nr) {
        recipeService.deleteById(nr);
        return ResponseEntity.ok("You deleted a recipe");
    }

    @PutMapping("/recipes/{nr}")
    public ResponseEntity updateRecipe(@PathVariable long nr, @RequestBody Recipe recipe) {
        return ResponseEntity.ok("Changed the Recipe");
    }

    @GetMapping("/recipes/{nr}/users")
    public ResponseEntity getRecipeUsers(@PathVariable long nr) {
        Iterable<User> recipeUsers = recipeService.getRecipeUsers(nr);
        return ResponseEntity.ok(recipeUsers);
    }

}
