package com.techelevator.model.teamuser;

import java.util.List;

import com.techelevator.model.team.Team;

public interface TeamUserDAO {
	
	public void updateUserByUserId(TeamUser teamUser);
	public List<TeamUser> listAllTeamsByUserId(int id);

}
