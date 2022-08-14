package com.signature.recipe.controller;

import com.signature.recipe.service.ImageService;
import com.signature.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

//@ExtendWith(SpringExtension.class)
//@Import({ImageService.class, RecipeService.class})
//@WebFluxTest(controllers = {ImageController.class, RecipeController.class})
class ImageControllerTest {

  @Mock
  public ImageService imageService;

  @Mock
  public RecipeService recipeService;

  private ImageController imageController;
  private RecipeController recipeController;

  @BeforeEach
  void setUp() throws Exception {
    try (AutoCloseable openMocks = MockitoAnnotations.openMocks(this)) {
      this.imageController = new ImageController(imageService);
      this.recipeController = new RecipeController(recipeService);
    }
  }

  private FilePart mockFilePart() {
    return new FilePart() {
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
  }

  @Test
  void handleImagePost() throws Exception {
//    final WebTestClient webTestClient = WebTestClient.bindToController(imageController).build();
//
//    webTestClient.post().uri("/recipe/1/image")
//            .contentType(MediaType.MULTIPART_FORM_DATA)
//            .body(Mono.just(mockFilePart()), FilePart.class)
//            .exchange().expectStatus().is3xxRedirection()
//            .expectHeader().valueEquals("Location", "/recipe/1/show");
//
//    verify(imageService, times(1)).saveImageFile(anyString(), any());
  }


  @Test
  public void renderImageFromDB() throws Exception {
//    final WebTestClient webTestClient = WebTestClient.bindToController(recipeController).build();
//
//    final String str = "fake image text";
//
//    //given
//    Recipe recipe = Recipe.builder().id("1").image(str.getBytes()).build();
//
//    when(recipeService.getById(anyString())).thenReturn(Mono.just(recipe));
//
//    //when
//    Recipe model = webTestClient.get().uri("/recipe/1/show")
//            .accept(MediaType.APPLICATION_JSON)
//            .exchange().expectStatus()
//            .isOk().expectBody(Recipe.class)
//            .returnResult().getResponseBody();
//
//    //then
//    verify(recipeService, times(1)).getById(anyString());
//
//    assert model != null;
//    assertEquals(str.getBytes().length, model.getImage().length);
  }
}