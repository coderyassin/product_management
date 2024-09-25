package org.alten.server.exception;

import org.alten.business.exception.ResourceNotCreatedException;
import org.alten.business.exception.ResourceNotDeletedException;
import org.alten.business.exception.ResourceNotFoundException;
import org.alten.business.exception.ResourceNotUpdatedException;
import org.alten.server.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(buildErrorMessage(e, request, NOT_FOUND));
    }

    @ExceptionHandler(ResourceNotCreatedException.class)
    public ResponseEntity<?> handleResourceNotCreatedException(ResourceNotCreatedException e, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(buildErrorMessage(e, request, BAD_REQUEST));
    }

    @ExceptionHandler(ResourceNotUpdatedException.class)
    public ResponseEntity<?> handleResourceNotUpdatedException(ResourceNotUpdatedException e, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(buildErrorMessage(e, request, BAD_REQUEST));
    }

    @ExceptionHandler(ResourceNotDeletedException.class)
    public ResponseEntity<?> handleResourceNotDeletedException(ResourceNotDeletedException e, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(buildErrorMessage(e, request, BAD_REQUEST));
    }

    private ErrorMessage buildErrorMessage(Exception e, WebRequest request, HttpStatus httpStatus) {
        return ErrorMessage.builder()
                .httpStatus(httpStatus)
                .timestamp(new Date())
                .message(e.getMessage())
                .description(request.getDescription(true))
                .build();
    }
}
