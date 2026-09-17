package com.famrut.farmer_management_api.exception;

public class VillageNotFoundException extends RuntimeException {

    public VillageNotFoundException(Long id) {
        super("Village not found with id: " + id);
    }
}