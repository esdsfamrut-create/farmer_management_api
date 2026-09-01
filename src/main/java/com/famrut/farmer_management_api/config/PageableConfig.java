package com.famrut.farmer_management_api.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PageableConfig {

    public static final String[] ALLOWED_SORT_FIELDS = {
            "name",
            "email",
            "phoneNumber"
    };
}