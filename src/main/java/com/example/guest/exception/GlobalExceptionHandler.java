package com.example.guest.exception;

import brave.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j(topic = "[GlobalExceptionHandler]")
public class GlobalExceptionHandler {

  @Autowired
  GuessServiceConverter converter;

  @Autowired
  Tracer tracer;

  @ExceptionHandler(GuestServiceException.class)
  public ResponseEntity<Object> handleGuestException(GuestServiceException exception) {
    log.error("GuestException", exception);
    return new ResponseEntity<>(
        converter.toJsonNode(exception.response, StringUtils.EMPTY, tracer),
        new HttpHeaders(),
        exception.response.getHttpStatus()
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleInvalidArgumentException(
      MethodArgumentNotValidException exception)
      throws JsonProcessingException {
    log.error("MethodArgumentNotValidException", exception);
    return new ResponseEntity<>(
        converter.toJsonNode(GuestServiceErrorResponse.INVALID_ARGUMENT,
                             exception
                                 .getBindingResult()
                                 .getAllErrors()
                                 .get(NumberUtils.INTEGER_ZERO)
                                 .getDefaultMessage(),
                             tracer
        ),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }
}
