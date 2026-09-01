package com.famrut.farmer_management_api.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;
import java.util.HashMap;


public class FarmerNotFoundException extends RuntimeException {

    public FarmerNotFoundException(Long id) {
        super("Farmer not found with id: " + id);
    }
}