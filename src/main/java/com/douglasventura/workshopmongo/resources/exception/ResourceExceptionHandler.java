package com.douglasventura.workshopmongo.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douglasventura.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

    Instant timestamp = Instant.now();
    HttpStatus status = HttpStatus.NOT_FOUND;
    String error = "Object not found!";
    String message = e.getMessage();
    String path = request.getRequestURI();

    StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
    return ResponseEntity.status(status).body(standardError);
  }
}
