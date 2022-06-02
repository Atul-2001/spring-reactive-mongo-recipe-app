package com.signature.recipe.service;

import com.signature.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class ImageService {

  private final RecipeRepository recipeRepository;

  public ImageService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Transactional
  public Mono<Void> saveImageFile(String recipeId, MultipartFile multipartFile) {
    return recipeRepository.findById(recipeId).doOnSuccess(recipe -> {
      if (Objects.isNull(recipe)) {
        log.info("Recipe not found for id : {}", recipeId);
      } else {
        try {
          recipe.setImage(multipartFile.getBytes());
        } catch (final IOException ex) {
          log.error("Failed to save image file", ex);
        }
      }
    }).mapNotNull(recipe -> {
      if (Objects.nonNull(recipe)) {
        recipeRepository.save(recipe).block();
      }
      return null;
    });
  }
}