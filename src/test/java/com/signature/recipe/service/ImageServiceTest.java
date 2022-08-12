package com.signature.recipe.service;

import com.signature.recipe.model.Recipe;
import com.signature.recipe.repository.RecipeRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ImageServiceTest {

  public ImageService imageService;

  @Mock
  public RecipeRepository recipeRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    this.imageService = new ImageService(recipeRepository);
  }

  @Test
  void saveImageFile() throws IOException {
    //given
    final String id = ObjectId.get().toString();
    final FilePart filePart = new FilePart() {
      @Override
      public String filename() {
        return "testing.txt";
      }

      @Override
      public Mono<Void> transferTo(Path dest) {
        return Mono.empty();
      }

      @Override
      public String name() {
        return "testing.txt";
      }

      @Override
      public HttpHeaders headers() {
        return HttpHeaders.EMPTY;
      }

      @Override
      public Flux<DataBuffer> content() {
        return DataBufferUtils.read(new ByteArrayResource("Spring Framework Guru"
                .getBytes(StandardCharsets.UTF_8)), new DefaultDataBufferFactory(), 1024);
      }
    };

    final Recipe recipe = Recipe.builder().id(id).build();

    when(recipeRepository.findById(anyString())).thenReturn(Mono.just(recipe));
    when(recipeRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));

    ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

    //when
    imageService.saveImageFile(id, Mono.just(filePart)).block();

    //then
    verify(recipeRepository, times(1)).save(argumentCaptor.capture());
    Recipe savedRecipe = argumentCaptor.getValue();
    DataBuffer dataBuffer = DataBufferUtils.join(filePart.content()).block();
    assertEquals(Objects.requireNonNull(dataBuffer).readableByteCount(), savedRecipe.getImage().length);
  }
}