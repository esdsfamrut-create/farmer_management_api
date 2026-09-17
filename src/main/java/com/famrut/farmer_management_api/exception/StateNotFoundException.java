package com.famrut.farmer_management_api.exception;

public class StateNotFoundException
        extends RuntimeException {

    public StateNotFoundException(Long id) {
        super("State not found with id: " + id);
    }
}