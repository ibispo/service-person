package com.bispo.firstprojectrestapi.validation;

import com.bispo.firstprojectrestapi.exception.PersonNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        return errorResponse(HttpStatus.BAD_REQUEST, request, exception);
    }

    @ExceptionHandler({
            HandlerMethodValidationException.class,
            ConstraintViolationException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiError> handleOtherRequestErrors(Exception exception, HttpServletRequest request) {
        return errorResponse(HttpStatus.BAD_REQUEST, request, exception);
    }

    @ExceptionHandler({
            SQLIntegrityConstraintViolationException.class
    })
    public ResponseEntity<ApiError> handlePersistenceErrors(Exception exception, HttpServletRequest request) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, exception);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ApiError> handlePersonNotFound(
            PersonNotFoundException exception,
            HttpServletRequest request
    ) {
        return errorResponse(HttpStatus.NOT_FOUND, request, exception);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException exception,
            HttpServletRequest request
    ) {
        return errorResponse(HttpStatus.METHOD_NOT_ALLOWED, request, exception);
    }

    private ResponseEntity<ApiError> errorResponse(HttpStatus status, HttpServletRequest request, Exception exception) {
        ApiError body = new ApiError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                request.getRequestURI(),
                exception.getLocalizedMessage()
        );
        return ResponseEntity.status(status).body(body);
    }

    private record ApiError(Instant timestamp, int status, String error, String path, String exception) {
    }
}
