package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * LanguageRequest
 */
public class LanguageRequest {
    
@NotBlank(message = "Code is required")
@Size(max = 10, message = "Code must not exceed 10 characters")
private String code;

@NotBlank(message = "Name is required")
@Size(max = 100, message = "Name must not exceed 100 characters")
private String name;

@NotBlank(message = "Native name is required")
@Size(max = 100, message = "Native name must not exceed 100 characters")
private String nativeName; 


public String getCode(){
    return code;
}

public void setCode(String code){
    this.code=code;
}

public String getName(){
    return name;
}

public void setName(String name){
    this.name=name;
}

public String getNativeName(){
    return nativeName;
}

public void setNativeName(String nativeName){
    this.nativeName=nativeName;
}
}