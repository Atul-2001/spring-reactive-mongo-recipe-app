package com.signature.recipe.repository;

import com.signature.recipe.model.Recipe;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends ReactiveCrudRepository<Recipe, String> {

}