package com.signature.recipe.converter;

import com.signature.recipe.data.UnitOfMeasureDTO;
import org.springframework.core.convert.converter.Converter;

public class StringToUnitOfMeasureDTOConverter implements Converter<String, UnitOfMeasureDTO> {
  @Override
  public UnitOfMeasureDTO convert(String source) {
    String[] split = source.split(":");
    if (split.length == 2) {
      return new UnitOfMeasureDTO(split[0], split[1]);
    } else {
      return new UnitOfMeasureDTO(split[0], "");
    }
  }
}