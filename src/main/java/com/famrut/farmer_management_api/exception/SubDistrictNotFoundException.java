package com.famrut.farmer_management_api.exception;

public class SubDistrictNotFoundException extends RuntimeException {

    public SubDistrictNotFoundException(Long id) {
        super("Sub-district not found with id: " + id);
    }
}