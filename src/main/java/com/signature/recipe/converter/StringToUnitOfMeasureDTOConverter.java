package com.signature.recipe.converter;

import com.signature.recipe.data.UnitOfMeasureDTO;
import org.springframework.core.convert.converter.Converter;

public class StringToUnitOfMeasureDTOConverter implements Converter<String, UnitOfMeasureDTO> {
  @Override
  public UnitOfMeasureDTO convert(String source) {
    String[] split = source.split(":");
    return new UnitOfMeasureDTO(split[0], split[1]);
  }
}
