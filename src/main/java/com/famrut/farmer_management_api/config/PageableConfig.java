package com.famrut.farmer_management_api.config;

import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

@Configuration
public class PageableConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static final String[] ALLOWED_SORT_FIELDS = {
            "name",
            "email",
            "phoneNumber"
    };
}