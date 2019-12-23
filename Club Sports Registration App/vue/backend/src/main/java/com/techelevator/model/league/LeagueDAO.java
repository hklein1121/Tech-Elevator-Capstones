package com.techelevator.model.league;

import java.util.List;

public interface LeagueDAO {

	public List<League> listAllLeaguesByOrganizationId(int id);
	public League getLeagueById(int id);
	public void createLeague(League league);
	public League updateLeagueById(League league);
	public void deleteLeagueById(int id);
}
