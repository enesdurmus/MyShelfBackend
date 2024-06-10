package com.enesd.myshelfbackend.config;

import com.enesd.myshelfbackend.model.exceptions.TokenRefreshException;
import com.enesd.myshelfbackend.model.exceptions.TooManyRequestException;
import com.enesd.myshelfbackend.model.exceptions.UnauthorizedException;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<GenericResponse<String>> handleExceptionFallback(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponse.error(exception.getMessage()));
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<GenericResponse<String>> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.error(exception.getMessage()));
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<GenericResponse<String>> handleUnauthorizedException(UnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenericResponse.error(exception.getMessage()));
    }

    @ExceptionHandler(value = {TokenRefreshException.class})
    public ResponseEntity<GenericResponse<String>> handleTokenRefreshException(TokenRefreshException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(GenericResponse.error(exception.getMessage()));
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<GenericResponse<String>> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(GenericResponse.error("Data Integrity Violation"));
    }

    @ExceptionHandler(value = {TooManyRequestException.class})
    public ResponseEntity<GenericResponse<String>> handleTooManyRequestException(TooManyRequestException exception) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(GenericResponse.error(exception.getMessage()));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<GenericResponse<String>> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(GenericResponse.error(exception.getMessage()));
    }
}
