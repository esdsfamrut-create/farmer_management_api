package com.famrut.farmer_management_api.exception;


public class CropNotFoundException extends RuntimeException {
    public CropNotFoundException(String message) {
        super(message);
    }
}