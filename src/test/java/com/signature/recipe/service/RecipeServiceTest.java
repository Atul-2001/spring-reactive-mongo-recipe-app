package com.signature.recipe.service;

import com.signature.recipe.exceptions.NotFoundException;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

class RecipeServiceTest {

  private RecipeService recipeService;

  @Mock
  public RecipeRepository recipeRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    recipeService = new RecipeService(recipeRepository);
  }

  @Test
  void getAllRecipes() {
    Recipe recipe = new Recipe();

    when(recipeRepository.findAll()).thenReturn(Flux.just(recipe));

    List<Recipe> recipes = recipeService.getAllRecipes().collectList().block();

    assertEquals(recipes.size(), 1);

    verify(recipeRepository, times(1)).findAll();
  }

  @Test
  void getById() {
    when(recipeRepository.findById(anyString()))
            .thenReturn(Mono.just(Recipe.builder().id("1L").build()));

    Recipe recipeReturned = recipeService.getById("1L").block();

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyString());
    verify(recipeRepository, never()).findAll();
  }


  @Test
  void delete() {
    Recipe recipe = Recipe.builder().id("1L").build();

    when(recipeRepository.delete(any())).thenReturn(Mono.empty());

    recipeService.delete(recipe).block();

    verify(recipeRepository, times(1)).delete(any());
  }

  @Test
  void deleteById() {
    when(recipeRepository.deleteById(anyString())).thenReturn(Mono.empty());

    recipeService.deleteById("1L").block();

    verify(recipeRepository, times(1)).deleteById(anyString());
  }

  @Test
  public void getRecipeByIdTestNotFound() {
    when(recipeRepository.findById(anyString())).thenReturn(Mono.empty());
    Assertions.assertThrows(NotFoundException.class, () -> recipeService.getById("1L").block());
  }
}