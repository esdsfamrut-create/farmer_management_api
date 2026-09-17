package com.famrut.farmer_management_api.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "sub_districts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_sub_district_district_code",
                        columnNames = {"district_id", "sub_district_code"}
                )
        }
)
public class SubDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_district_code", nullable = false, length = 20)
    private String subDistrictCode;

    @Column(name = "sub_district_name", nullable = false, length = 100)
    private String subDistrictName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @Column(nullable = false)
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public String getSubDistrictCode() {
        return subDistrictCode;
    }

    public void setSubDistrictCode(String subDistrictCode) {
        this.subDistrictCode = subDistrictCode;
    }

    public String getSubDistrictName() {
        return subDistrictName;
    }

    public void setSubDistrictName(String subDistrictName) {
        this.subDistrictName = subDistrictName;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}