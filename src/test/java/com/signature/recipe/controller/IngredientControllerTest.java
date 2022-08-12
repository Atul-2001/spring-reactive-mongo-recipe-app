package com.signature.recipe.controller;

import com.signature.recipe.service.IngredientService;
import com.signature.recipe.service.RecipeService;
import com.signature.recipe.service.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IngredientControllerTest {

  @Mock
  public RecipeService recipeService;
  @Mock
  public IngredientService ingredientService;
  @Mock
  public UnitOfMeasureService unitOfMeasureService;
  public IngredientController ingredientController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    this.ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
  }

  @Test
  void listIngredients() throws Exception {
//    //given
//    Recipe recipe = Recipe.builder().id("1").build();
//
//    when(recipeService.getById(anyString())).thenReturn(recipe);
//
//    //when
//    mockMvc.perform(get("/recipe/1/ingredients"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("/recipe/ingredient/index"))
//            .andExpect(model().attribute("recipe", instanceOf(RecipeDTO.class)));
//
//    //then
//    verify(recipeService, times(1)).getById(anyString());
  }

  @Test
  void getIngredient() throws Exception {
//    //given
//    IngredientDTO ingredient = new IngredientDTO();
//    ingredient.setRecipeId("1");
//
//    //when
//    when(ingredientService.getByRecipeAndId(anyString(), anyString())).thenReturn(ingredient);
//
//    //then
//    mockMvc.perform(get("/recipe/1/ingredient/2/show"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("/recipe/ingredient/show"))
//            .andExpect(model().attribute("ingredient", instanceOf(IngredientDTO.class)));
  }

  @Test
  void updateRecipeIngredient() throws Exception {
//    //given
//    IngredientDTO ingredientDTO = new IngredientDTO();
//
//    //when
//    when(ingredientService.getByRecipeAndId(anyString(), anyString())).thenReturn(ingredientDTO);
//    when(unitOfMeasureService.getAll()).thenReturn(new ArrayList<>());
//
//    //then
//    mockMvc.perform(get("/recipe/1/ingredient/2/update"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("/recipe/ingredient/form"))
//            .andExpect(model().attributeExists("ingredient"))
//            .andExpect(model().attributeExists("unitOfMeasures"));
  }

  @Test
  void saveOrUpdate() throws Exception {
//    //given
//    IngredientDTO ingredientDTO = new IngredientDTO();
//    ingredientDTO.setId("3");
//    ingredientDTO.setRecipeId("2");
//
//    //when
//    when(ingredientService.saveOrUpdate(any())).thenReturn(ingredientDTO);
//
//    //then
//    mockMvc.perform(post("/recipe/2/ingredient")
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .param("id", "")
//                    .param("description", "some string"))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
  }

  @Test
  void newRecipe() throws Exception {
//    //given
//    Recipe recipe = Recipe.builder().id("1").build();
//
//    //when
//    when(recipeService.getById(anyString())).thenReturn(recipe);
//    when(unitOfMeasureService.getAll()).thenReturn(new ArrayList<>());
//
//    //then
//    mockMvc.perform(get("/recipe/1/ingredient/new"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("/recipe/ingredient/form"))
//            .andExpect(model().attributeExists("ingredient"))
//            .andExpect(model().attributeExists("unitOfMeasures"));
//
//    verify(recipeService, times(1)).getById(anyString());
  }

  @Test
  void deleteIngredient() throws Exception {
//    mockMvc.perform(get("/recipe/2/ingredient/3/delete"))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(view().name("redirect:/recipe/2/ingredients"));
//
//    verify(ingredientService, times(1))
//            .deleteByRecipeAndId(anyString(), anyString());
  }
}