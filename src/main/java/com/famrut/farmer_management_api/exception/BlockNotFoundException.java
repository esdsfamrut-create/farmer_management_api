package com.famrut.farmer_management_api.exception;

public class BlockNotFoundException extends RuntimeException {

    public BlockNotFoundException(Long id) {
        super("Block not found with id: " + id);
    }
}