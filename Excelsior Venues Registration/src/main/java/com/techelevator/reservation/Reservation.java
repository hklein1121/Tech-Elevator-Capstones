package com.techelevator.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {

	private long reservationId;
	private long spaceId;
	private long numberOfAttendees;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reservedFor;
	private String venueName;
	private String spaceName;
	private BigDecimal dailyRate;

	public BigDecimal getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(long spaceId) {
		this.spaceId = spaceId;
	}

	public long getNumberOfAttendees() {
		return numberOfAttendees;
	}

	public void setNumberOfAttendees(long numberOfAttendees) {
		this.numberOfAttendees = numberOfAttendees;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReservedFor() {
		return reservedFor;
	}

	public void setReservedFor(String reservedFor) {
		this.reservedFor = reservedFor;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", spaceId=" + spaceId + ", numberOfAttendees="
				+ numberOfAttendees + ", startDate=" + startDate + ", endDate=" + endDate + ", reservedFor="
				+ reservedFor + "]";
	}

}
