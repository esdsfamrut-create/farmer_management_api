package com.famrut.farmer_management_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FarmCropResponse {

	private Long id;

	private Long farmId;
	private String farmName;

	private Long cropId;
	private String cropName;
	private String cropVariety;

	private BigDecimal cultivatedArea;
	private String season;
	private LocalDate sowingDate;
	private String areaUnit;
	private String status;

	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFarmId() {
		return farmId;
	}

	public void setFarmId(Long farmId) {
		this.farmId = farmId;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public Long getCropId() {
		return cropId;
	}

	public void setCropId(Long cropId) {
		this.cropId = cropId;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getCropVariety() {
		return cropVariety;
	}

	public void setCropVariety(String cropVariety) {
		this.cropVariety = cropVariety;
	}

	public BigDecimal getCultivatedArea() {
		return cultivatedArea;
	}

	public void setCultivatedArea(BigDecimal cultivatedArea) {
		this.cultivatedArea = cultivatedArea;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public LocalDate getSowingDate() {
		return sowingDate;
	}

	public void setSowingDate(LocalDate sowingDate) {
		this.sowingDate = sowingDate;
	}

	public String getAreaUnit() {
		return areaUnit;
	}

	public void setAreaUnit(String areaUnit) {
		this.areaUnit = areaUnit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
