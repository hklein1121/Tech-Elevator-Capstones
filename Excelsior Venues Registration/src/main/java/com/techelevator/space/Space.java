package com.techelevator.space;

import java.math.BigDecimal;

public class Space {
	private long id;
	private long venueId;
	private String name;
	private boolean isAccessible;
	private long openFrom;
	private long openTo;
	private BigDecimal dailyRate;
	private long maxOccupancy;
	private String venueName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAccessible() {
		return isAccessible;
	}

	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}

	public long getOpenFrom() {
		return openFrom;
	}

	public void setOpenFrom(long openFrom) {
		this.openFrom = openFrom;
	}

	public long getOpenTo() {
		return openTo;
	}

	public void setOpenTo(long openTo) {
		this.openTo = openTo;
	}

	public BigDecimal getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}

	public long getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(long maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

}
