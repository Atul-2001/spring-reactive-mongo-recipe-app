package com.signature.recipe.repository;

import com.signature.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class UnitOfMeasureRepositoryTest {

  @Autowired
  public UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  void setUp() {
    unitOfMeasureRepository.save(new UnitOfMeasure("")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Cup")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Cups")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Dash")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Each")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Pint")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Ripe")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Small")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Ounce")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Pinch")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Medium")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Teaspoon")).block();
    unitOfMeasureRepository.save(new UnitOfMeasure("Tablespoon")).block();
  }

  @Test
  @DirtiesContext
  void findByDescription() {
    Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon").blockOptional();

    assertNotNull(unitOfMeasure.orElse(null));

    assertEquals("Tablespoon", unitOfMeasure.get().getDescription());
  }

  @Test
  @DirtiesContext
  void findByDescription2() {
    Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Medium").blockOptional();

    assertNotNull(unitOfMeasure.orElse(null));

    assertEquals("Medium", unitOfMeasure.get().getDescription());
  }
}