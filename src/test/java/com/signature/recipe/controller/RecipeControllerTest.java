package com.signature.recipe.controller;

import com.signature.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RecipeControllerTest {

  @Mock
  public RecipeService recipeService;
  public RecipeController recipeController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    recipeController = new RecipeController(recipeService);
  }

  @Test
  void showById() throws Exception {
//    Recipe recipe = Recipe.builder().id("1").build();
//
//    when(recipeService.getById(anyString())).thenReturn(recipe);
//
//    mockMvc.perform(get("/recipe/1/show"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("recipe/show"))
//            .andExpect(model().attribute("recipe", instanceOf(Recipe.class)));
  }

  @Test
  public void createRecipe() throws Exception {
//    mockMvc.perform(get("/recipe/new"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("recipe/form"))
//            .andExpect(model().attribute("recipe", instanceOf(RecipeDTO.class)));
  }

  @Test
  public void updateRecipe() throws Exception {
//    when(recipeService.getById(anyString())).thenReturn(Recipe.builder().id("1").build());
//
//    mockMvc.perform(get("/recipe/1/update"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("recipe/form"))
//            .andExpect(model().attribute("recipe", instanceOf(RecipeDTO.class)));
  }

  @Test
  public void addOrUpdate() throws Exception {
//    when(recipeService.save(any())).thenReturn(Recipe.builder().id("2").build());
//    when(recipeService.getById(anyString())).thenReturn(Recipe.builder().id("2").build());
//
//    mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .param("id", "2")
//                    .param("directions", "some directions")
//                    .param("description", "some descriptions"))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(view().name("redirect:/recipe/2/show"));
  }

  @Test
  void deleteRecipe() throws Exception {
//    mockMvc.perform(get("/recipe/1/delete"))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(view().name("redirect:/"));
//
//    verify(recipeService, times(1)).deleteById(anyString());
  }

  @Test
  public void testGetRecipeNotFound() throws Exception {
//    when(recipeService.getById(anyString())).thenThrow(NotFoundException.class);
//
//    mockMvc.perform(get("/recipe/1/show")).andExpect(status().isNotFound());
  }

  @Test
  public void testGetRecipeNotFound2() throws Exception {
//    when(recipeService.getById(anyString())).thenThrow(NotFoundException.class);
//
//    mockMvc.perform(get("/recipe/1/show"))
//            .andExpect(status().isNotFound())
//            .andExpect(view().name("404"));
  }

/*  @Test
  public void testGetRecipeNumberFormat() throws Exception {
    mockMvc.perform(get("/recipe/4a/show"))
            .andExpect(status().isBadRequest())
            .andExpect(view().name("400"));
  }*/

  @Test
  public void testPostNewRecipeFormValidationFail() throws Exception {
//    Recipe recipe = Recipe.builder().id("2").build();
//
//    when(recipeService.save(any())).thenReturn(recipe);
//
//    mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .param("id", "2").param("cookTime", "3000"))
//            .andExpect(status().isOk()).andExpect(model().attributeExists("recipe"))
//            .andExpect(view().name("recipe/form"));
  }
}