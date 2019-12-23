package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LeagueNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2191365369562677388L;
	
	private int leagueId;
	
	public LeagueNotFoundException (int leagueId, String message) {
		super(message);
		setLeagueId(leagueId);
	}

	
	public int getLeagueId() {
		return leagueId;
	}
	
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
}