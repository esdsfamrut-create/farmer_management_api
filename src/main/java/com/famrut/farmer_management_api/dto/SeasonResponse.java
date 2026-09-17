package com.famrut.farmer_management_api.dto;

/**
 * SeasonResponse
 */
public class SeasonResponse {
    private Long id;
    private String code;
    private String name;
    private Integer startMonth;
    private Integer endMonth;
    private boolean active;
    

       public Long getId(){
        return id;
    }
        
    public void setId(Long id){
        this.id=id;
    }

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

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active=active;
    }
}