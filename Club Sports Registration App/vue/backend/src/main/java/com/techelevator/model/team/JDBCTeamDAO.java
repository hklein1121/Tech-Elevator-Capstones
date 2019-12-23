package com.techelevator.model.team;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.organization.Organization;
import com.techelevator.model.user.User;

@Component
public class JDBCTeamDAO implements TeamDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCTeamDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
    @Override
    public List<Team> getAllTeamsUserIsNotOn() {
        List<Team> teams = new ArrayList<Team>();
        String sqlSelectAllUsers = "SELECT team.name FROM team LEFT JOIN team_users ON team.team_id=team_users.team_id WHERE team_users.team_id IS NULL;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);

        while (results.next()) {
            Team theTeam = mapRowToTeamList(results);
            teams.add(theTeam);
        }

        return teams;
    }

	@Override
	public List<Team> listAllTeamsByLeagueId(int id) {
		
		ArrayList<Team> teams = new ArrayList<>();
		
		String SelectAllSql = "SELECT * FROM team WHERE league_id = ? ORDER BY name ASC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(SelectAllSql, id);
		while (results.next()) {
			Team theTeam = mapRowToTeam(results);
			teams.add(theTeam);
		}
		return teams;
	}
	
	@Override
	public List<Team> listAllTeamsByUserId(int id) {
		
		ArrayList<Team> teams = new ArrayList<>();
		
		String SelectAllSql = "SELECT team.name FROM team LEFT JOIN team_users ON team.team_id = team_users.team_id WHERE team_users.user_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(SelectAllSql, id);
		while (results.next()) {
			Team theTeam = mapRowToTeam(results);
			teams.add(theTeam);
		}
		return teams;
	}
	
	@Override
	public Team getTeamById(int id) {
		
		Team team = null;
		String selectSql = "SELECT * FROM team WHERE team_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, id);
		if(results.next()) {
			team = mapRowToTeam(results);			
		}
		
		return team;
	}

	@Override
	public void createTeam(Team team) {
		
		String insertSql = "INSERT INTO team (name, team_email, primary_venue, secondary_venue, league_id) VALUES (?, ?, ?, ?, ?);";
		jdbcTemplate.update(insertSql, team.getName(), team.getEmail(), team.getPrimaryVenue(), team.getSecondaryVenue(), team.getLeagueId());
	}

	@Override
	public Team updateTeam(Team team) {
		
		String updateSql = "UPDATE team SET name = ?, team_email = ?, primary_venue = ?, secondary_venue = ? WHERE team_id = ?;";
		jdbcTemplate.update(updateSql, team.getName(), team.getEmail(), team.getPrimaryVenue(), team.getSecondaryVenue(), team.getTeamId());
		return team;
	}

	@Override
	public void deleteTeam(int id) {
		
		String deleteSql = "DELETE FROM team WHERE team_id = ?;";
		jdbcTemplate.update(deleteSql, id);
	}

	private Team mapRowToTeam(SqlRowSet row) {
		Team team = new Team();
		team.setTeamId(row.getInt("team_id"));
		team.setName(row.getString("name"));
		team.setEmail(row.getString("team_email"));
		team.setPrimaryVenue(row.getString("primary_venue"));
		team.setSecondaryVenue(row.getString("secondary_venue"));
		team.setLeagueId(row.getInt("league_id"));
		return team;
	}
	
	private Team mapRowToTeamList(SqlRowSet row) {
		Team team = new Team();
		team.setTeamId(row.getInt("team_id"));
		team.setName(row.getString("name"));
		return team;
	}

	@Override
	public List<Team> listAllTeams() {
		ArrayList<Team> teams = new ArrayList<>();
		
		String selectAllSql = "SELECT * FROM team ORDER BY name ASC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectAllSql);
		while (results.next()) {
			Team theTeam = mapRowToTeam(results);
			teams.add(theTeam);
		}
		return teams;
	}
}