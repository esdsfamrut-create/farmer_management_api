package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class FarmCropRequest {

	@NotNull(message = "Farm ID is required")
	private Long farmId;

	@NotNull(message = "Crop ID is required")
	private Long cropId;

	@NotNull(message = "Cultivated area is required")
	private BigDecimal cultivatedArea;

	@NotNull(message = "Season is required")
    private String season;

	@NotNull(message = "Sowing date is required")
    private LocalDate sowingDate;

	@NotNull(message = "Area unit is required")
    private String areaUnit;

	public FarmCropRequest() {
	}

	public FarmCropRequest(Long farmId, Long cropId, BigDecimal cultivatedArea,
			String season, LocalDate sowingDate, String areaUnit) {
		this.farmId = farmId;
		this.cropId = cropId;
		this.cultivatedArea = cultivatedArea;
		this.season = season;
		this.sowingDate = sowingDate;
		this.areaUnit = areaUnit;
	}

	public Long getFarmId() {
		return farmId;
	}

	public void setFarmId(Long farmId) {
		this.farmId = farmId;
	}

	public Long getCropId() {
		return cropId;
	}

	public void setCropId(Long cropId) {
		this.cropId = cropId;
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
}
