package com.famrut.farmer_management_api.dto;

public class AuthResponse {

    private String message;
    private String accessToken;
    private String refreshToken;
    private Long farmerId;

    public AuthResponse(
            String message,
            String accessToken,
            String refreshToken,
            Long farmerId) {

        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.farmerId = farmerId;
    }

    public String getMessage() {
        return message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getFarmerId() {
        return farmerId;
    }
}