package com.techelevator.model.team;

public class Team {

	private int teamId;
	private String name;
	private String email;
	private String primaryVenue;
	private String secondaryVenue;
	private int leagueId;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrimaryVenue() {
		return primaryVenue;
	}
	public void setPrimaryVenue(String primaryVenue) {
		this.primaryVenue = primaryVenue;
	}
	public String getSecondaryVenue() {
		return secondaryVenue;
	}
	public void setSecondaryVenue(String secondaryVenue) {
		this.secondaryVenue = secondaryVenue;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + leagueId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((primaryVenue == null) ? 0 : primaryVenue.hashCode());
		result = prime * result + ((secondaryVenue == null) ? 0 : secondaryVenue.hashCode());
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
		Team other = (Team) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (leagueId != other.leagueId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (primaryVenue == null) {
			if (other.primaryVenue != null)
				return false;
		} else if (!primaryVenue.equals(other.primaryVenue))
			return false;
		if (secondaryVenue == null) {
			if (other.secondaryVenue != null)
				return false;
		} else if (!secondaryVenue.equals(other.secondaryVenue))
			return false;
		if (teamId != other.teamId)
			return false;
		return true;
	}
	

}	