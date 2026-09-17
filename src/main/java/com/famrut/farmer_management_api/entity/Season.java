package com.famrut.farmer_management_api.entity;

import java.lang.annotation.Inherited;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="season",uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_season_code",
            columnNames = {"code"}
        )
    })
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;   
    
    @Column(name="code",nullable =false,length =20,unique = true)
    String code;  
    
    @Column(name="name",nullable =false,length =100)
    String  name;

    @Column(name="start_month",nullable =false)
    int  startMonth;

    @Column(name="end_month",nullable =false)
    int  endMonth;

    @Column(name="active",nullable =false)
    boolean  active=true;

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