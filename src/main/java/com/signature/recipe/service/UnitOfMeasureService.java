package com.signature.recipe.service;

import com.signature.recipe.data.UnitOfMeasureDTO;
import com.signature.recipe.model.UnitOfMeasure;
import com.signature.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class UnitOfMeasureService {

  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public UnitOfMeasureService(UnitOfMeasureRepository unitOfMeasureRepository) {
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  public Flux<UnitOfMeasureDTO> getAll() {
    return unitOfMeasureRepository.findAll().map(UnitOfMeasure::getDTO);
  }
}