package com.famrut.farmer_management_api.dto;

public class AuthResponse {

    private String message;
    private String accessToken;
    private Long farmerId;

    public AuthResponse(
            String message,
            String accessToken,
            Long farmerId) {

        this.message = message;
        this.accessToken = accessToken;
        this.farmerId = farmerId;
    }

    public String getMessage() {
        return message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getFarmerId() {
        return farmerId;
    }
}