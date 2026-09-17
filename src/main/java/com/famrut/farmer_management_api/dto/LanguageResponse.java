package com.famrut.farmer_management_api.dto;

/**
 * LanguageResponse
 */
public class LanguageResponse {

    private Long id;
    private String code;
    private String name;
    private String nativeName;
    private boolean active;

    public LanguageResponse(     Long id,
     String code,
     String name,
     String nativeName,
     boolean active){
        this.id=id;
        this.code=code;
        this.name=name;
        this.nativeName=nativeName;
        this.active=active;
     }

     public LanguageResponse() {
}


 public Long getId(){
            return id;
        }

        public void setId(Long id){
            this.id=id;
        }


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

        public boolean isActive(){
            return active;
        }

        public void setActive(boolean active){
            this.active=active;
        }
 
}