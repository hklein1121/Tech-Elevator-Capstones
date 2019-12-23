package com.techelevator.model.availability;






public class Availability {
	
	private int availabilityId;
	private int teamId;
	private boolean isHome;
	private boolean isAway;
	private String date;
	private String earliestStart;
	private String latestStart;
	
	
	
	public int getAvailabilityId() {
		return availabilityId;
	}
	public void setAvailabilityId(int availabilityId) {
		this.availabilityId = availabilityId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public boolean isHome() {
		return isHome;
	}
	public void setHome(boolean isHome) {
		this.isHome = isHome;
	}
	public boolean isAway() {
		return isAway;
	}
	public void setAway(boolean isAway) {
		this.isAway = isAway;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEarliestStart() {
		return earliestStart;
	}
	public void setEarliestStart(String earliestStart) {
		this.earliestStart = earliestStart;
	}
	public String getLatestStart() {
		return latestStart;
	}
	public void setLatestStart(String latestStart) {
		this.latestStart = latestStart;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availabilityId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((earliestStart == null) ? 0 : earliestStart.hashCode());
		result = prime * result + (isAway ? 1231 : 1237);
		result = prime * result + (isHome ? 1231 : 1237);
		result = prime * result + ((latestStart == null) ? 0 : latestStart.hashCode());
		result = prime * result + teamId;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Availability other = (Availability) obj;
		if (availabilityId != other.availabilityId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (earliestStart == null) {
			if (other.earliestStart != null)
				return false;
		} else if (!earliestStart.equals(other.earliestStart))
			return false;
		if (isAway != other.isAway)
			return false;
		if (isHome != other.isHome)
			return false;
		if (latestStart == null) {
			if (other.latestStart != null)
				return false;
		} else if (!latestStart.equals(other.latestStart))
			return false;
		if (teamId != other.teamId)
			return false;
		return true;
	}
}