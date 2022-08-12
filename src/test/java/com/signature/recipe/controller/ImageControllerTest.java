package com.signature.recipe.controller;

import com.signature.recipe.service.ImageService;
import com.signature.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ImageControllerTest {

  @Mock
  public ImageService imageService;

  @Mock
  public RecipeService recipeService;

  private ImageController imageController;
  private RecipeController recipeController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    this.imageController = new ImageController(imageService);
    this.recipeController = new RecipeController(recipeService);
  }

  @Test
  void handleImagePost() throws Exception {
//    final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
//
//    final MockMultipartFile multipartFile = new MockMultipartFile("file", "testing.txt",
//            "text/plain", "Spring Framework Guru".getBytes());
//
//    mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(header().string("Location", "/recipe/1/show"));
//
//    verify(imageService, times(1)).saveImageFile(anyString(), any());
  }


  @Test
  public void renderImageFromDB() throws Exception {
//    final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
//
//    final String str = "fake image text";
//
//    //given
//    Recipe recipe = Recipe.builder().id("1").image(str.getBytes()).build();
//
//    when(recipeService.getById(anyString())).thenReturn(recipe);
//
//    //when
//    ModelAndView modelAndView = mockMvc.perform(get("/recipe/1/show"))
//            .andExpect(status().isOk()).andReturn().getModelAndView();
//
//    //then
//    verify(recipeService, times(1)).getById(anyString());
//
//    assert modelAndView != null;
//    Recipe model = (Recipe) modelAndView.getModel().get("recipe");
//
//    assertEquals(str.getBytes().length, model.getImage().length);
  }
}