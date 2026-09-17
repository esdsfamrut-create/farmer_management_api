package com.famrut.farmer_management_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "language", uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_code",
            columnNames = {"code"}
        )
    })
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name="name",length=100)
    private String name;

    @Column(name="nativeName  ",length=100)
    private String nativeName;

    @Column(nullable = false)
    private Boolean active;

    public Long getId(){
        return id;
    }

    public void setId(Long Id){
        this.id=Id;
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

    public Boolean isActive(){
        return active;
    }

    public void setActive(Boolean active){
        this.active=active;
    }

}