package com.example.demo.service;

import com.example.demo.FoodkeeperApplication;
import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes={FoodkeeperApplication.class})
public class RecipeServiceIntegrationTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    Recipe recipe;

    @Mock
    private RecipeRepository recipeRepository;

    @Captor
    ArgumentCaptor<Recipe> recipeCaptor;

    @Test
    void testAddRecipe() {

        //ARRANGE
        recipe = new Recipe();
        recipe.setRecipeName("taart");
        recipe.setRecipeIngredient("meel bloem en water");
        recipe.setRecipeDescription("bak alles in de oven");

        recipeRepository.save(recipe);

        //ACT
        verify(recipeRepository, times(1)).save(recipeCaptor.capture());
        var recipeTaart = recipeCaptor.getValue();

        //ASSERT
        assertThat(recipeTaart.getRecipeName()).isEqualTo("taart");
        assertThat(recipeTaart.getRecipeIngredient()).isEqualTo("meel bloem en water");
        assertThat(recipeTaart.getRecipeDescription()).isEqualTo("bak alles in de oven");

    }

    @Test
    public void testGetRecipeById() {
        //ARRANGE
        recipe = new Recipe();
        recipe.setRecipeId(1L);

        //ACT
        Mockito.when(recipeRepository.existsById(1L)).thenReturn(true);

        Long expectedUserId = 1L;

        //ASSERT
        recipeService.getRecipeById(1L);
        assertEquals(expectedUserId, recipe.getRecipeId());
    }


}

