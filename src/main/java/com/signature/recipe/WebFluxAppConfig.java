package com.signature.recipe;

import com.signature.recipe.converter.StringToUnitOfMeasureDTOConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxAppConfig implements WebFluxConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new StringToUnitOfMeasureDTOConverter());
  }
}