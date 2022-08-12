package com.signature.recipe.controller;

import com.signature.recipe.data.IngredientDTO;
import com.signature.recipe.data.UnitOfMeasureDTO;
import com.signature.recipe.exceptions.NotFoundException;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.service.IngredientService;
import com.signature.recipe.service.RecipeService;
import com.signature.recipe.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}")
public class IngredientController {

  private final RecipeService recipeService;
  private final IngredientService ingredientService;

  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(final RecipeService recipeService,
                              final IngredientService ingredientService,
                              final UnitOfMeasureService unitOfMeasureService) {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @InitBinder("ingredient")
  protected void initBinder(WebDataBinder binder) {
//    binder.addCustomFormatter(new StringToUnitOfMeasureConverter(), "");
  }

  @GetMapping("/ingredient/new")
  public Mono<String> newRecipe(@PathVariable String recipeId, Model model) {
    //make sure we have a good id value
    return recipeService.getById(recipeId)
            .switchIfEmpty(Mono.error(new NotFoundException("Recipe not found for id : " + recipeId)))
            .flatMap(recipe -> {
              //need to return parent id for hidden form property
              IngredientDTO ingredientDTO = new IngredientDTO();
              ingredientDTO.setRecipeId(recipeId);
              ingredientDTO.setUnitOfMeasure(new UnitOfMeasureDTO());

              model.addAttribute("ingredient", ingredientDTO);
              model.addAttribute("unitOfMeasures", unitOfMeasureService.getAll());

              return Mono.just("/recipe/ingredient/form");
            });
  }

  @GetMapping("/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {
    log.debug("Getting ingredient list for recipe id: " + recipeId);
    final Mono<Recipe> recipe = recipeService.getById(recipeId);
    model.addAttribute("recipe", recipe.map(Recipe::getDTO));
    return "/recipe/ingredient/index";
  }

  @GetMapping("/ingredient/{ingredientId}/show")
  public Mono<String> getIngredient(final Model model,
                                    @PathVariable String recipeId,
                                    @PathVariable String ingredientId) {
    log.debug("Getting ingredient for id {} of recipe id {} ", ingredientId, recipeId);
    return ingredientService.getByRecipeAndId(recipeId, ingredientId)
            .flatMap(ingredient -> {
              if (Objects.isNull(ingredient)) {
                return Mono.just("/recipe/ingredient/index");
              } else {
                model.addAttribute("ingredient", ingredient);
                return Mono.just("/recipe/ingredient/show");
              }
            });
  }

  @GetMapping("/ingredient/{id}/update")
  public String updateRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,
                                       final Model model) {
    model.addAttribute("ingredient", ingredientService.getByRecipeAndId(recipeId, id));

    model.addAttribute("unitOfMeasures", unitOfMeasureService.getAll());
    return "/recipe/ingredient/form";
  }

  @PostMapping("ingredient")
  public Mono<String> saveOrUpdate(@ModelAttribute IngredientDTO ingredient) {
    return ingredientService.saveOrUpdate(ingredient).flatMap(ingredientDTO -> {
      log.debug("saved ingredient id:" + ingredientDTO.getId());
      return Mono.just(String.format("redirect:/recipe/%s/ingredient/%s/show",
              ingredientDTO.getRecipeId(), ingredientDTO.getId()));
    });
  }

  @GetMapping("/ingredient/{id}/delete")
  public Mono<String> deleteIngredient(@PathVariable String recipeId, @PathVariable String id) {
    return ingredientService.deleteByRecipeAndId(recipeId, id).flatMap(ingredient -> {
      log.debug("deleted ingredient id:" + id);
      return Mono.just(String.format("redirect:/recipe/%s/ingredients", recipeId));
    });
  }
}