package com.techelevator.model.team;

import java.util.List;

public interface TeamDAO {

	public List<Team> listAllTeamsByLeagueId(int id);
	
	public List<Team> listAllTeamsByUserId(int id);
	public List<Team> listAllTeams();
	public Team getTeamById(int id);
	public void createTeam(Team team);
	public Team updateTeam(Team team);
	public void deleteTeam(int id);
	List<Team> getAllTeamsUserIsNotOn();
}