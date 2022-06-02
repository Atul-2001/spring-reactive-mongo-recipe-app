package com.signature.recipe.service;

import com.signature.recipe.data.RecipeDTO;
import com.signature.recipe.exceptions.NotFoundException;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.repository.RecipeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeService(RecipeRepository recipeRepository) {
    log.debug("Loading Recipe Service...");
    this.recipeRepository = recipeRepository;
  }

  public Mono<Recipe> save(RecipeDTO recipeDTO) {
    log.debug("Saving new recipe with description : {}", recipeDTO.getDescription());
    return recipeRepository.save(recipeDTO.getModel());
  }

  public Flux<Recipe> getAllRecipes() {
    log.debug("Getting list of recipes...");
    return recipeRepository.findAll();
  }

  public Mono<Recipe> getById(final String id) {
    log.debug("Getting recipe for id : " + id);
    return recipeRepository.findById(id).switchIfEmpty(Mono
            .error(new NotFoundException("Recipe Not Found for id : " + id)));
  }

  public Mono<Recipe> getById(final String id, Boolean isNew) {
    log.debug("Getting recipe for id : " + id);
    if (isNew) {
      return recipeRepository.findById(id).switchIfEmpty(Mono.empty());
    } else {
      return recipeRepository.findById(id).switchIfEmpty(Mono
              .error(new NotFoundException("Recipe Not Found for id : " + id)));
    }
  }

  public Mono<Void> delete(final Recipe recipe) {
    log.debug("Deleting recipe using recipe model");
    return recipeRepository.delete(recipe);
  }

  public Mono<Void> deleteById(final String id) {
    log.debug("Deleting recipe for id : " + id);
    return recipeRepository.deleteById(id);
  }
}