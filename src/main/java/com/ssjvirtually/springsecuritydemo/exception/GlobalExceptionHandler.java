package com.ssjvirtually.springsecuritydemo.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String DUPLICATE_KEY_MESSAGE_TEMPLATE = "Duplicate value for field '%s'. Please choose a unique value.";

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        
             String constraintName = ((ConstraintViolationException)ex.getCause()).getConstraintName();
             System.out.println(constraintName);
            
        Exception exception  = ex; 
        String violatedConstraint = extractViolatedConstraint(ex.getMessage());
        if (violatedConstraint != null) {
            String fieldName = getFieldNameFromConstraint(violatedConstraint);
            String errorMessage = String.format(DUPLICATE_KEY_MESSAGE_TEMPLATE, fieldName);
            return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.BAD_REQUEST);
        }

        // Handle other potential causes of DataIntegrityViolationException
        // (e.g., NOT NULL constraint violations)
        String defaultMessage = "An integrity constraint violation occurred.";
        return new ResponseEntity<>(new ErrorResponse(defaultMessage), HttpStatus.BAD_REQUEST);
    }

    private String extractViolatedConstraint(String exceptionMessage) {
        // Implement logic to extract the violated constraint from the exception message
        // This can be database-specific, so you might need regular expressions or parsing
        // based on your database dialect (e.g., "constraint [constraint_name]" for MySQL)
        // Here's a simplified example for illustrative purposes:
        int constraintStartIndex = exceptionMessage.indexOf("constraint ");
        if (constraintStartIndex != -1) {
            return exceptionMessage.substring(constraintStartIndex + "constraint ".length()).trim();
        }
        return null;
    }

    private String getFieldNameFromConstraint(String constraint) {
        // Implement logic to extract the field name from the violated constraint
        // This can also be database-specific, so you might need parsing based on your dialect
        // Here's a simplified example for illustrative purposes:
        int fieldStartIndex = constraint.indexOf("(");
        if (fieldStartIndex != -1) {
            int fieldEndIndex = constraint.indexOf(")");
            if (fieldEndIndex > fieldStartIndex) {
                return constraint.substring(fieldStartIndex + 1, fieldEndIndex).trim();
            }
        }
        return null;
    }

    // Class to hold the error response structure
    public static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
