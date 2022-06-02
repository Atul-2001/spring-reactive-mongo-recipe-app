package com.signature.recipe.service;

import com.signature.recipe.data.IngredientDTO;
import com.signature.recipe.model.Ingredient;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.repository.RecipeRepository;
import com.signature.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class IngredientService {

  private final RecipeRepository recipeRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public IngredientService(final RecipeRepository recipeRepository,
                           final UnitOfMeasureRepository unitOfMeasureRepository) {
    this.recipeRepository = recipeRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  public Mono<IngredientDTO> saveOrUpdate(final IngredientDTO ingredientDTO) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDTO.getRecipeId()).blockOptional();
    if (recipeOptional.isEmpty()) {
      log.error("Recipe not found for id: " + ingredientDTO.getRecipeId());
      return Mono.just(new IngredientDTO());
    } else {
      Recipe recipe = recipeOptional.get();

      Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
              .findFirst();

      if (ingredientOptional.isPresent()) {
        Ingredient ingredientFound = ingredientOptional.get();
        ingredientFound.setDescription(ingredientDTO.getDescription());
        ingredientFound.setAmount(ingredientDTO.getAmount());
        ingredientFound.setUnit(unitOfMeasureRepository
                .findById(ingredientDTO.getUnitOfMeasure().getId())
                .switchIfEmpty(Mono.error(new RuntimeException("UOM NOT FOUND"))).block());
      } else {
        recipe.addIngredient(ingredientDTO.getModel());
      }

      final Recipe savedRecipe = recipeRepository.save(recipe).block();

      ingredientOptional = savedRecipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
              .findFirst();

      if (ingredientOptional.isEmpty()) {
        ingredientOptional = savedRecipe.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientDTO.getDescription()))
                .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientDTO.getAmount()))
                .filter(recipeIngredients -> recipeIngredients.getUnit().getId().equals(ingredientDTO.getUnitOfMeasure().getId()))
                .findFirst();
      }

      return Mono.justOrEmpty(ingredientOptional.isEmpty() ? null
              : ingredientOptional.get().getDTO().setRecipeId(ingredientDTO.getRecipeId()));
    }
  }

  public Mono<IngredientDTO> getByRecipeAndId(final String recipeId, final String ingredientId) {
    return recipeRepository.findById(recipeId).flatMapIterable(Recipe::getIngredients)
            .filter(ingredient -> StringUtils.equalsIgnoreCase(ingredient.getId(), ingredientId))
            .single().map(ingredient -> {
              IngredientDTO ingredientDTO = ingredient.getDTO();
              ingredientDTO.setRecipeId(recipeId);
              return ingredientDTO;
            });
  }

  public Mono<Void> deleteByRecipeAndId(final String recipeId, final String ingredientId) {
    log.debug("Deleting ingredient : " + recipeId + " : " + ingredientId);

    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId).blockOptional();

    if (recipeOptional.isPresent()) {
      Recipe recipe = recipeOptional.get();

      Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();

      if (ingredientOptional.isPresent()) {
        Ingredient ingredient = ingredientOptional.get();
        recipe.getIngredients().remove(ingredient);
        recipeRepository.save(recipe).block();
      } else {
        log.debug("Ingredient not found for id : " + ingredientId);
      }
    } else {
      log.debug("Recipe not found fo id : " + recipeId);
    }
    return Mono.empty();
  }
}