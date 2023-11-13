package io.com.resume.exception;

import java.util.List;

public record ErrorResponse(
        String message,
        List<ValidationError> errors) {

    public record ValidationError(
            String field,
            String message) {
    }
}
