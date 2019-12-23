package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5407554505613484679L;
	
	private int teamId;
	
	public TeamNotFoundException (int teamId, String message) {
		super(message);
		setTeamId(teamId);
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}	
}