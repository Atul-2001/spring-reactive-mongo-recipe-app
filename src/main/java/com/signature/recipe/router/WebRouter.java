package com.signature.recipe.router;

import com.signature.recipe.model.Recipe;
import com.signature.recipe.service.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebRouter {

  @Bean
  public RouterFunction<?> routes(RecipeService recipeService) {
    return RouterFunctions.route(RequestPredicates.GET("/api/v1/recipes"),
            request -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(recipeService.getAllRecipes(), Recipe.class));
  }
}