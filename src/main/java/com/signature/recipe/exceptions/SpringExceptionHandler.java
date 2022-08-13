package com.signature.recipe.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.thymeleaf.exceptions.TemplateInputException;
import reactor.core.publisher.Mono;


@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
  public Mono<String> handleNotFoundException(Model model, Exception ex) {
    log.error("Handling not found or null exception");
    log.error("Message : {}", ex.getMessage());
    model.addAttribute("ex", ex);
    return Mono.just("404");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({NumberFormatException.class, WebExchangeBindException.class})
  public Mono<String> handlerNumberFormatException(Model model, Exception ex) {
    log.error("Handling data binding exception");
    log.error("Message : {}", ex.getMessage());
    model.addAttribute("ex", ex);
    return Mono.just("400");
  }
}