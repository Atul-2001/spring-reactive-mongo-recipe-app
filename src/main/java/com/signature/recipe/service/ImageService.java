package com.signature.recipe.service;

import com.signature.recipe.model.Recipe;
import com.signature.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class ImageService {

  private final RecipeRepository recipeRepository;

  public ImageService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Transactional
  public Mono<Recipe> saveImageFile(String recipeId, Mono<FilePart> filePart) {
    return recipeRepository.findById(recipeId).flatMap(recipe -> {
      if (Objects.isNull(recipe)) {
        log.info("Recipe not found for id : {}", recipeId);
        return Mono.empty();
      } else {
        return filePart.flatMap(dataBuffer -> DataBufferUtils.join(dataBuffer.content())
                .flatMap(data -> recipeRepository.save(recipe.setImage(data.asByteBuffer().array()))));
      }
    });
  }
}