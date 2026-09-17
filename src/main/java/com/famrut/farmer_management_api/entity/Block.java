package com.famrut.farmer_management_api.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "blocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_block_sub_district_code",
                        columnNames = {"sub_district_id", "block_code"}
                )
        }
)
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "block_code", nullable = false, length = 20)
    private String blockCode;

    @Column(name = "block_name", nullable = false, length = 100)
    private String blockName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_district_id", nullable = false)
    private SubDistrict subDistrict;

    @Column(nullable = false)
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public SubDistrict getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}