package com.signature.recipe.repository;

import com.signature.recipe.model.Category;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, String> {

  Mono<Category> findByDescription(String description);

  Mono<Category> findByDescription(Publisher<String> description);
}