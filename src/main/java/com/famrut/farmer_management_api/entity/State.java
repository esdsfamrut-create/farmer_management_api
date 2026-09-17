package com.famrut.farmer_management_api.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "states",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "state_code")
        }
)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state_code", nullable = false, unique = true)
    private String stateCode;

    @Column(name = "state_name", nullable = false)
    private String stateName;

    @Column(nullable = false)
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}