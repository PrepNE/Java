package com.mikepn.vehiclemanagementsystem.handler;



import com.mikepn.vehiclemanagementsystem.exceptions.OperationNotPermittedException;
import com.mikepn.vehiclemanagementsystem.payload.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


import static com.mikepn.vehiclemanagementsystem.enums.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException e) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ACCOUNT_LOCKED.getCode())
                                .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                                .error(e.getMessage())
                                .build()
                );
    }


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException e) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ACCOUNT_DISABLED.getCode())
                                .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                                .error(e.getMessage())
                                .build()
                );
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException() {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BAD_CREDENTIALS.getCode())
                                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                                .error("Login and / or Password is incorrect")
                                .build()
                );
    }


    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Internal server error, please contact the admin")
                                .error(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity
                .status(FORBIDDEN)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(UNAUTHORIZED_ACTION.getCode())
                                .businessErrorDescription(UNAUTHORIZED_ACTION.getDescription())
                                .error("You are not authorized to perform this action")
                                .build()
                );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Validation failed")
                                .validationErrors(errors)
                                .build()
                );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        String message = "Duplicate field value violates unique constraint";

        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof SQLException sqlEx) {
            String sqlMessage = sqlEx.getMessage();
            if (sqlMessage != null && sqlMessage.contains("users_national_id_key")) {
                message = "A user with this national ID already exists";
            } else if (sqlMessage != null && sqlMessage.contains("users_email_key")) {
                message = "A user with this email already exists";
            }
        }

        return ApiResponse.fail("Failed to create admin", HttpStatus.BAD_REQUEST, message);
    }



}
