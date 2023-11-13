package io.com.resume.exception;

import io.com.resume.user.exception.InvalidUserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResumeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity handleConflict(InvalidUserException ex, WebRequest request) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message, null);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}