package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.*;


/**
 * SeasonRequest
 */
public class SeasonRequest {

    @NotBlank(message = "Code is required")
    @Size(max = 20)
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotNull(message = "Start month is required")
    @Min(value = 1, message = "Start month must be between 1 and 12")
    @Max(value = 12, message = "Start month must be between 1 and 12")
    private int startMonth;

    @NotNull(message = "End month is required")
    @Min(value = 1, message = "End month must be between 1 and 12")
    @Max(value = 12, message = "End month must be between 1 and 12")
    private int endMonth;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code=code;
    }

     public int getStartMonth(){
        return startMonth;
    }

    public void setStartMonth(int startMonth){
        this.startMonth=startMonth;
    }

    public int getEndMonth(){
        return endMonth;
    }

    public void setEndMonth(int endMonth){
        this.endMonth=endMonth;
    }
}