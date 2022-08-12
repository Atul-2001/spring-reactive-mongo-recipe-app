package com.signature.recipe.service;

import com.signature.recipe.data.IngredientDTO;
import com.signature.recipe.model.Ingredient;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.repository.RecipeRepository;
import com.signature.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
    return recipeRepository.findById(ingredientDTO.getRecipeId()).flatMap(recipe -> {
      AtomicReference<Ingredient> ingredientAtomicReference = new AtomicReference<>(recipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId())).findFirst().orElse(null));

      if (ingredientAtomicReference.get() != null) {
        Ingredient ingredientFound = ingredientAtomicReference.get();
        ingredientFound.setDescription(ingredientDTO.getDescription());
        ingredientFound.setAmount(ingredientDTO.getAmount());

        unitOfMeasureRepository.findById(ingredientDTO.getUnitOfMeasure().getId())
                .publishOn(Schedulers.immediate()).doOnNext(ingredientFound::setUnit)
                .switchIfEmpty(Mono.error(new RuntimeException("UOM NOT FOUND"))).subscribe();
      } else {
        recipe.addIngredient(ingredientDTO.getModel());
      }

      return recipeRepository.save(recipe).flatMap(savedRecipe -> {
        ingredientAtomicReference.set(savedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                .findFirst().orElse(null));

        if (ingredientAtomicReference.get() == null) {
          ingredientAtomicReference.set(savedRecipe.getIngredients().stream()
                  .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientDTO.getDescription()))
                  .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientDTO.getAmount()))
                  .filter(recipeIngredients -> recipeIngredients.getUnit().getId().equals(ingredientDTO.getUnitOfMeasure().getId()))
                  .findFirst().orElse(null));
        }

        return Mono.justOrEmpty(ingredientAtomicReference.get() == null ? null :
                ingredientAtomicReference.get().getDTO().setRecipeId(ingredientDTO.getRecipeId()));
      });
    }).switchIfEmpty(new Mono<IngredientDTO>() {
      @Override
      public void subscribe(CoreSubscriber<? super IngredientDTO> actual) {
        log.error("Recipe not found for id: " + ingredientDTO.getRecipeId());
        actual.onNext(new IngredientDTO());
      }
    });
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

  public Mono<Recipe> deleteByRecipeAndId(final String recipeId, final String ingredientId) {
    log.debug("Deleting ingredient : " + recipeId + " : " + ingredientId);

    return recipeRepository.findById(recipeId).switchIfEmpty(new Mono<>() {
      @Override
      public void subscribe(CoreSubscriber<? super Recipe> actual) {
        log.debug("Recipe not found fo id : " + recipeId);
      }
    }).flatMap(recipe -> {
      Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();

      if (ingredientOptional.isPresent()) {
        Ingredient ingredient = ingredientOptional.get();
        recipe.getIngredients().remove(ingredient);
        return recipeRepository.save(recipe);
      } else {
        log.debug("Ingredient not found for id : " + ingredientId);
        return Mono.empty();
      }
    });
  }
}