package com.famrut.farmer_management_api.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "villages",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_village_block_code",
                        columnNames = {"block_id", "village_code"}
                )
        }
)
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "village_code", nullable = false, length = 20)
    private String villageCode;

    @Column(name = "village_name", nullable = false, length = 150)
    private String villageName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;

    @Column(nullable = false)
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}