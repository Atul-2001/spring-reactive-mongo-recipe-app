package com.signature.recipe.controller;

import com.signature.recipe.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class ImageController {

  private final ImageService imageService;

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping("/recipe/{recipeId}/image")
  public Mono<String> uploadImage(@PathVariable String recipeId,
                                  @RequestPart("file") Mono<FilePart> filePart) {
    log.info("Uploading image file for recipe id : {}", recipeId);

    return imageService.saveImageFile(recipeId, filePart).then(new Mono<String>() {
      @Override
      public void subscribe(CoreSubscriber<? super String> actual) {
        log.info("Image file uploaded successfully for recipe id : {}", recipeId);
        actual.onNext(String.format("redirect:/recipe/%s/show", recipeId));
        actual.onComplete();
      }
    });
  }
}