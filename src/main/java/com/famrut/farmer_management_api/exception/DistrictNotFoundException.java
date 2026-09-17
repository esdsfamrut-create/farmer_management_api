package com.famrut.farmer_management_api.exception;

public class DistrictNotFoundException extends RuntimeException {

    public DistrictNotFoundException(Long id) {
        super("District not found with id: " + id);
    }
}