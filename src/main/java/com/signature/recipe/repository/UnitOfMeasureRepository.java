package com.signature.recipe.repository;

import com.signature.recipe.model.UnitOfMeasure;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UnitOfMeasureRepository extends ReactiveCrudRepository<UnitOfMeasure, String> {

  Mono<UnitOfMeasure> findByDescription(String description);

  Mono<UnitOfMeasure> findByDescription(Publisher<String> description);
}