package br.vitorreck.payment.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handleBusinessException(BusinessException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
  }

  @ExceptionHandler(HttpServerErrorException.class)
  public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
  }
}
