package com.famrut.farmer_management_api.exception;

import com.famrut.farmer_management_api.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import com.famrut.farmer_management_api.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FarmerNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleFarmerNotFound(
            FarmerNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );

        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), response),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation failed", response),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ApiResponse<ErrorResponse>> handleIllegalArgumentException(
        IllegalArgumentException ex) {

    ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            null
    );

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Illegal argument provided", errorResponse));
}

        @ExceptionHandler(StateNotFoundException.class)
        public ResponseEntity<ApiResponse<ErrorResponse>> handleStateNotFound(
                StateNotFoundException ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        null
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), errorResponse));
        }

        @ExceptionHandler(DistrictNotFoundException.class)
public ResponseEntity<ApiResponse<Void>> handleDistrictNotFound(
        DistrictNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                new ApiResponse<>(
                    404,
                    ex.getMessage()
                )
            );
}

@ExceptionHandler(SubDistrictNotFoundException.class)
public ResponseEntity<ApiResponse<Void>> handleSubDistrictNotFound(
        SubDistrictNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                    new ApiResponse<>(
                            404,
                            ex.getMessage()
                    )
            );
}

@ExceptionHandler(BlockNotFoundException.class)
public ResponseEntity<ApiResponse<Void>> handleBlockNotFound(
        BlockNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                    new ApiResponse<>(
                            404,
                            ex.getMessage()
                    )
            );
}

@ExceptionHandler(VillageNotFoundException.class)
public ResponseEntity<ApiResponse<Void>> handleVillageNotFound(
        VillageNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                    new ApiResponse<>(
                            404,
                            ex.getMessage()
                    )
            );
}

@ExceptionHandler(CropNotFoundException.class)
public ResponseEntity<ApiResponse<Void>> handleCropNotFound(
        CropNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiResponse<>(
                                404,
                                ex.getMessage()
                        )
                );      
        }
}