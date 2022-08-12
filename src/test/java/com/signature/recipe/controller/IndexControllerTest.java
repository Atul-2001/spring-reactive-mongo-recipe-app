package com.signature.recipe.controller;

import com.signature.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

class IndexControllerTest {

  public IndexController controller;

  @Mock
  public RecipeService service;

  @Mock
  public Model model;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new IndexController(service);
  }

  @Test
  void home() {
//    String page = controller.home(model);
//    assertEquals(page, "index");
//    verify(service, times(1)).getAllRecipes();
//    verify(model, times(1)).addAttribute(eq("recipes"), anyList());
  }

  @Test
  void home2() {
//    List<Recipe> recipes = new ArrayList<>();
//    recipes.add(Recipe.builder().id("1").build());
//    recipes.add(Recipe.builder().id("2").build());
//
//    when(service.getAllRecipes()).thenReturn(recipes);
//
//    ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);
//
//    String viewName = controller.home(model);
//
//    assertEquals(viewName, "index");
//
//    verify(service, times(1)).getAllRecipes();
//    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
//
//    List<Recipe> listInArgument = argumentCaptor.getValue();
//
//    assertEquals(2, listInArgument.size());
  }

  @Test
  void testMockMVC() throws Exception {
//    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//    mockMvc.perform(MockMvcRequestBuilders.get("/"))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.view().name("index"));
  }
}