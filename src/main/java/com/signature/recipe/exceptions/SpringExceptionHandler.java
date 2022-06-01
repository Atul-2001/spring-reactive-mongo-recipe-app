package com.signature.recipe.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;


@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringExceptionHandler {

/*  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ModelAndView handleNotFoundException(NotFoundException ex) {
    log.error("Handling not found exception");
    log.error("Message : {}", ex.getMessage());
    return new ModelAndView("404").addObject("ex", ex);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView handlerNumberFormatException(NumberFormatException ex) {
    log.error("Handling number format exception");
    log.error("Message : {}", ex.getMessage());
    return new ModelAndView("400").addObject("ex", ex);
  }*/
}