package com.signature.recipe.controller;

import com.signature.recipe.model.Recipe;
import com.signature.recipe.router.WebRouter;
import com.signature.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

public class RouterFunctionTest {

  private WebTestClient webTestClient;

  @Mock
  public RecipeService recipeService;

  @BeforeEach
  void setUp() throws Exception {
    try (AutoCloseable openMocks = MockitoAnnotations.openMocks(this)) {
      WebRouter webRouter = new WebRouter();

      RouterFunction<?> routerFunction = webRouter.routes(recipeService);

      this.webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }
  }

  @Test
  void testGetRecipes() {
    when(recipeService.getAllRecipes())
            .thenReturn(Flux.just());

    webTestClient.get().uri("/api/v1/recipes")
            .accept(MediaType.APPLICATION_JSON)
            .exchange().expectStatus().isOk();
  }

  @Test
  void testGetRecipesWithData() {
    when(recipeService.getAllRecipes())
            .thenReturn(Flux.just(new Recipe()));

    webTestClient.get().uri("/api/v1/recipes")
            .accept(MediaType.APPLICATION_JSON)
            .exchange().expectStatus().isOk()
            .expectBodyList(Recipe.class);
  }
}