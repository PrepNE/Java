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
    public ResponseEntity<ApiResponse<Object>> handleLockedException(LockedException e) {
        return ApiResponse.fail(
                ACCOUNT_LOCKED.getDescription(),
                UNAUTHORIZED,
                e.getMessage()
        );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponse<Object>> handleDisabledException(DisabledException e) {
        return ApiResponse.fail(
                ACCOUNT_DISABLED.getDescription(),
                UNAUTHORIZED,
                e.getMessage()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException() {
        return ApiResponse.fail(
                BAD_CREDENTIALS.getDescription(),
                UNAUTHORIZED,
                "Login and / or Password is incorrect"
        );
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ApiResponse<Object>> handleOperationNotPermittedException(OperationNotPermittedException e) {
        return ApiResponse.fail(
                "Operation not permitted",
                FORBIDDEN,
                e.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        return ApiResponse.fail(
                "Validation failed",
                BAD_REQUEST,
                errors
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

        return ApiResponse.fail("Failed to create admin", BAD_REQUEST, message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException e) {
        return ApiResponse.fail(
                UNAUTHORIZED_ACTION.getDescription(),
                FORBIDDEN,
                "You are not authorized to perform this action"
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception e) {
        e.printStackTrace();
        return ApiResponse.fail(
                "Internal server error, please contact the admin",
                INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
    }
}
